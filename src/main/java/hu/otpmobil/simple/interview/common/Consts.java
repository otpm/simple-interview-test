package hu.otpmobil.simple.interview.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Consts {

	public static final String CONS_100000 = "100000";
	public static final String CONS_100090 = "100090";

	public static final String CNTRY_HU = "HU";

	public static final String CCY_HUF = "HUF";

	public static final String LNUM_317205000 = "317205000";
	public static final String LNUM_382298000 = "382298000";
	public static final String LNUM_449050000 = "449050000";
	public static final String LNUM_441700000 = "441700000";
	public static final String LNUM_442291500 = "442291500";
	public static final String LNUM_522863410 = "522863410";
	public static final String LNUM_442294000 = "442294000";
	public static final String LNUM_442291200 = "442291200";

	public static final String PCON_0001 = "0001";

	public static final String SPECIAL_CODE_G = "G";

	// version information related
	public static final String JMS_DESTINATION_NAME = "sysLedger";

	public static final String COMMON_SERVICE_IN_JMS_REQUEST_HANDLER_LISTENER = "commonServiceInJmsRequestHandlerListener";

	public static final String GET_VERSION_RQ = "GET_VERSION_RQ";

	public static final List<String> COMMON_SERVICE_IN_LISTEN_JMSTYPES = unmodifiableList(
			asList(GET_VERSION_RQ));

	public static final String MESSAGE_LOG_TOPIC = "EH.messageFlow.LEDGER";

}
