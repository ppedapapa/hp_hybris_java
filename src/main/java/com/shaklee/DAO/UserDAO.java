package com.shaklee.DAO;

import java.util.List;

import com.shaklee.entity.User;

public interface UserDAO {
	public User findUser(String userId);

	public Integer findUserRank(String userId);

	public List<User> findSponsoring(String userId);

	public int getRelativeLevel(String rootUserId, String downlineUserId);

	public void updateEmailAddress(User user, String emailAddress);

	public void updateTelephoneNumber(User user, String telephoneNumber);

	public boolean isEmailUnique(User user, String emailAddress);

	public boolean isUplineManger(String managerId, String downlineUserId);

	public String getUplineId(String customer);

	public String getExistingUser(String email, String lastName,
			String addressLine1, String postalCode);

	public String getIdForEmail(String email);

	public String getPartyId(String userId);

	public String getUplineBL(String userId);

	public String getUplineDist(String userId);

	public String getTitleCode(String userId);

	public boolean isBL(String userId);

	public String getUserId(String partyId);

	public boolean isDownline(String sponsorId, String downlineId);

	public boolean checkValidContactCreator(String contactId, String shakleeId)
			throws Exception;

	int getUserRank(String shakleeId);
	
	public User findByEmail(String email);
}
