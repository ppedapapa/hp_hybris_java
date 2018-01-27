package com.shaklee.shared.dao.mobile;

import static com.shaklee.shared.util.DataUtils.titleCase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Component;

import com.shaklee.common.util.StringUtils;
import com.shaklee.common.util.cache.CachingLoader;
import com.shaklee.common.util.cache.Loader;
import com.shaklee.common.util.PropertiesObject;
import com.shaklee.shared.dao.JdbcTemplateDAO;
import com.shaklee.shared.data.Country2;
import com.shaklee.shared.data.Language;
import com.shaklee.shared.data.user.CompPlan;
import com.shaklee.shared.data.user.Phone;
import com.shaklee.shared.data.user.Phone.PHONE_TYPE;
import com.shaklee.shared.data.user.Rank;
import com.shaklee.shared.data.user.UserBase;
import com.shaklee.shared.data.user.UserBase.Profile;
import com.shaklee.shared.data.user.UserBase.User;
import com.shaklee.shared.data.user.UserResource;

/**
 * @author jkuriakos
 * 
 */
@Component
public class MobileProfileDAO extends JdbcTemplateDAO {

	final static PropertiesObject PROP = new PropertiesObject(MobileProfileDAO.class);
	final static String GET_MOBILE_USER_PROFILE = PROP.getSql("GET_MOBILE_USER_PROFILE");
	final static String ALL_RANKS = PROP.getSql("ALL_RANKS");
	final static String GET_PROFILE_PHOTO = PROP.getSql("GET_PROFILE_PHOTO");

	@Autowired
	public MobileProfileDAO(DataSource dataSource) {
		super(dataSource);
	}

	private static final ParameterizedRowMapper<UserResource> userResourceMapper = ParameterizedBeanPropertyRowMapper
			.newInstance(UserResource.class);

	UserResource profilePhoto(String userId) {
		return queryForObject(GET_PROFILE_PHOTO, userResourceMapper, userId);
	}

	ParameterizedRowMapper<Profile> mobileProfileMapper = new ParameterizedRowMapper<Profile>() {

		@Override
		public Profile mapRow(ResultSet rs, int i) throws SQLException {
			// user
			Profile p = new Profile();
			p.shaklee_id = rs.getString(1);
			p.first_name = titleCase(rs.getString(2));
			p.last_name = titleCase(rs.getString(3));
			p.rank_id = rs.getInt(4);
			p.email = rs.getString(5);
			p.is_gold_ambassador = "Y".equals(rs.getString(6));
			p.language = Language.fromItrack(rs.getInt(7));
			p.country = Country2.getBy3LetterCode(rs.getString(8));
			p.phones = createPhoneList(rs.getString(9), rs.getString(10));

			{
				// PWS domain
				String pwsPath = rs.getString(11);
				if (pwsPath != null) {
					p.pws_domain = pwsPath + '.' + rs.getString(12) + ".com";
					p.pws_url = pwsUrl(p);
				}
			}

			// business leader
			User bl = p.business_leader = new User();
			bl.shaklee_id = rs.getString(13);
			bl.first_name = titleCase(rs.getString(14));
			bl.last_name = titleCase(rs.getString(15));
			bl.email = rs.getString(16);
			bl.phones = createPhoneList(rs.getString(17), rs.getString(18));
			p.comp_plan = CompPlan.value(rs.getString(19));
			p.legacy_grandfathered_rank = rs.getString(20);

			p.profile_photo = UserResource.create(rs.getString(21), rs.getString(22));

			return p;
		}
	};

	/**
	 * Calculates the actual localized PWS url
	 */
	private static String pwsUrl(Profile p) {
		return "https://" + p.pws_domain + '/' + p.country.mcCountryUrl + '/' + p.language + '/';
	}

	public static List<Phone> createPhoneList(String number, String type) {
		Phone phone = Phone.createPhone(StringUtils.clean(number), type);
		if (phone == null)
			return null;

		return Arrays.asList(phone);
	}

	public static List<Phone> createLegacyPhoneList(String number, String type) {
		Phone phone = Phone.createLegacyContact(number, type);
		if (phone == null)
			return null;

		return Arrays.asList(phone);
	}

	public Profile getMobileProfile(String shakleeId) {
		Profile profile = loadMobileProfile(shakleeId);
		cleanEmail(profile);
		cleanEmail(profile.business_leader);

		// get actual rank
		Rank rank = getRank(Country2.us, profile.rank_id);
		if (rank != null) {
			profile.rank_code = rank.code;
			profile.is_bl = rank.is_bl;
		}
		// legacy title
		if (profile.legacy_grandfathered_rank != null) {
			Rank legacyRank = getRank(Country2.us, profile.rank_id);
			// Key coordinator or above
			if (legacyRank.id >= 13)
				profile.is_grandfathered_rank = true;
		}
		return profile;
	}

	private Profile loadMobileProfile(String id) {
		Profile p = jdbcTemplate.queryForObject(GET_MOBILE_USER_PROFILE, mobileProfileMapper, id);
		p.business_leader.profile_photo = profilePhoto(p.business_leader.shaklee_id);
		return p;
	}

	public static void cleanEmail(UserBase user) {
		if (user.email != null && StringUtils.endsWithIgnoreCase(user.email, "@example.com"))
			user.email = null;
	}

	private static final ParameterizedRowMapper<Rank> rankMapper = new ParameterizedRowMapper<Rank>() {

		private final ParameterizedRowMapper<Rank> propMapper = ParameterizedBeanPropertyRowMapper
				.newInstance(Rank.class);

		@Override
		public Rank mapRow(ResultSet rs, int i) throws SQLException {
			Rank rank = propMapper.mapRow(rs, i);
			String isBl = rs.getString("IS_BL");
			rank.is_bl = "Y".equals(isBl);
			return rank;
		}
	};

	public List<Rank> getAllRanks(Country2 country) {
		return ranksCache.get(country);
	}

	private static final List<Integer> excludeNextRankUs = Arrays.asList(5);

	private List<Rank> loadRanks(Country2 country) {
		int min, max;
		String exclude = "-1";

		if (country == Country2.us || country == Country2.ca) {
			min = 1;
			max = 17;
			exclude = "3,4,6";
		} else if (country == Country2.mx) {
			min = 20;
			max = 30;
		} else
			throw new IllegalArgumentException("Unsupported country " + country);

		String sql = ALL_RANKS.replace("??", exclude);
		final List<Rank> ranks = jdbcTemplate.query(sql, rankMapper, min, max);
		updateNextRanks(ranks);
		return ranks;
	}

	private static void updateNextRanks(final List<Rank> ranks) {
		for (int i = ranks.size() - 2; i >= 0; i--) {
			Rank rank = ranks.get(i);
			Rank next = ranks.get(i + 1);
			if (excludeNextRankUs.contains(next.id))
				// skip one
				rank.next = ranks.get(i + 2).code;
			else
				// do not skip
				rank.next = next.code;
		}
	}

	public Rank getRank(Country2 country, int rankId) {
		List<Rank> ranks = ranksCache.get(country);
		for (Rank rank : ranks) {
			if (rank.id == rankId)
				return rank;
		}

		return null;
	}

	public Rank getRank(Country2 country, String code) {
		List<Rank> ranks = ranksCache.get(country);
		for (Rank rank : ranks) {
			if (code.equals(rank.code))
				return rank;
		}

		return null;
	}

	CachingLoader<Country2, List<Rank>> ranksCache = new CachingLoader<Country2, List<Rank>>(
			new Loader<Country2, List<Rank>>() {

				@Override
				public List<Rank> get(Country2 key) {
					return loadRanks(key);
				}
			}, 3600);

	private static AtomicInteger randomType = new AtomicInteger(0);

	private static final Phone.PHONE_TYPE randomType() {
		int rnd = randomType.incrementAndGet();
		switch (rnd % 3) {
		case 0:
			return PHONE_TYPE.HOME;
		case 1:
			return PHONE_TYPE.MOBILE;
		}
		return null;
	}
}