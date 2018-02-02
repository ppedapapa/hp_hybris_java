package com.shaklee.rulesets.healthQuestionaire.components;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.shaklee.promo.Action;
import com.shaklee.promo.PromoRequest;
import com.shaklee.promo.RequestLog;
import com.shaklee.promo.basic.AbstractComponent;
import com.shaklee.rulesets.healthQuestionaire.Questions;

@Component
public class DebugMessage extends AbstractComponent<PromoRequest<Questions>>implements Action<PromoRequest<Questions>> {

	private static final Logger logger = LoggerFactory.getLogger(DebugMessage.class);
	private static final boolean isDebugEnabled = logger.isDebugEnabled();

	@NotNull
	public String message;

	@Override
	public void exec(PromoRequest<Questions> r) {
		if (isDebugEnabled && r instanceof RequestLog) {
			RequestLog log = (RequestLog) r;
			log.debug(message);
		}
	}
}
