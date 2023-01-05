/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huytvq.utils;

/**
 *
 * @author Admin
 */
public class MyConstants {

    //paging
    public static final int TOTAL_ITEM_IN_PAGE = 20;

    //Status
    public static final int STATUS_NEW = 1,
            STATUS_ACTIVE = 2,
            STATUS_INACTIVE = 3,
            STATUS_ACCEPT = 4,
            STATUS_DELETE = 5;

    public static final String INVALID_PAGE = "invalidPage";

    public class LOGIN_FEATURE {

        public static final String VERIFY_PAGE_HTML = "verifyPageHTML";
        public static final String HOME_PAGE_ACTION = "homePageAction";
        public static final String LOGIN_PAGE = "loginPage";
    }

    public class RESEND_CODE_FEATURE {
        public static final String VERIFY_PAGE_HTML = "verifyPageHTML";
    }
    
    public class LOGOUT_FEATURE {

        public static final String LOGIN_PAGE = "";
    }

    public class VERIFY_FEATURE {

        public static final String VERIFY_PAGE_JSP = "verifyPageJSP";
        public static final String HOME_PAGE_ACTION = "homePageAction";
    }

    public class REGISTER_FEATURE {

        public static final String REGISTER_PAGE = "registerPageJSP";
        public static final String LOGIN_PAGE = "";
    }

    public class HOMEPAGE_FEATURE {

        public static final String HOME_PAGE = "homePage";
    }

    public class SEARCH_FEATURE {
        public static final String HOME_PAGE = "homePage";
    }

    public class MAKE_EMOTION_FEATURE {
        public static final String VIEW_DETAIL_ACTION = "viewDetailAction";
    }
    
    public class VIEW_DETAIL_FEATURE {
        public static final String VIEW_DETAIL_PAGE = "viewDetailPage";
    }
    
    public class DELETE_ARTICLE_FEATURE {
        public static final String VIEW_DETAIL_ACTION = "viewDetailAction";
    }
    
    public class DELETE_COMMENT_FEATURE {
        public static final String VIEW_DETAIL_ACTION = "viewDetailAction";
    }
    
    public class POST_COMMENT_FEATURE {
        public static final String VIEW_DETAIL_ACTION = "viewDetailAction";
    }
    
    public class POST_ARTICLE_FEATURE {
        public static final String HOME_PAGE_ACTION = "homePageAction";
    }
    
    public class USER_PROFILE_FEATURE {
        public static final String USER_PROFILE_PAGE = "userProfilePage";
    }
}
