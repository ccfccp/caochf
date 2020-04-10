package com.ccf.selenium.data.defData;

/**
 * 网站信息.
 */
public class WebSite {
    /**
     * 网站标识.
     */
    private String siteId;
    /**
     * 网站首页.
     */
    private String indexUrl;
    /**
     * 网站名称.
     */
    private String siteName;
    /**
     * 描述信息.
     */
    private String siteDesc;

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getIndexUrl() {
        return indexUrl;
    }

    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteDesc() {
        return siteDesc;
    }

    public void setSiteDesc(String siteDesc) {
        this.siteDesc = siteDesc;
    }
}
