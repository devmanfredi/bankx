package com.xcar.constants;

public interface UrlConstants {

    String URL_PREFIX = "/rest";

    String URL_BANKSLIP = URL_PREFIX + "/bankslips";

    String URL_BANKSLIP_BY_ID = URL_PREFIX + "/bankslips/{id}";

    String URL_BANKSLIP_PAY = URL_PREFIX + "/bankslips/{id}/pay";

    String URL_BANKSLIP_CANCEL = URL_PREFIX + "/bankslips/{id}/cancel";

}
