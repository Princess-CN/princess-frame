package huacloud.jwdsj.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import princess.common.security.AuthToken;
import princess.common.security.Principal;

/**
 * 权限验证类<br>
 * @author YYL
 */
public class AuthRealm {

    // ==============================Fields===========================================
    // @Autowired
    // private XtRyService xtRyService;
    //
    // @Autowired
    // private XtJsService xtJsService;
    //
    // @Autowired
    // private XtGnService xtGnService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ==============================Constructors=====================================
    public AuthRealm() {
        super();
    }

    // ==============================Methods==========================================
    /**
     * (登陆验证)认证回调函数,登录时调用.
     * @param
     * @return 认证信息
     */
    protected Principal doGetAuthenticationInfo(AuthToken token) {

        // String username = token.getUsername();
        // String password = token.getPassword();
        //
        // if (StringUtils.isEmpty(username)) {
        // throw ExceptionHelper.prompt("用户名不能为空");
        // }
        // if (StringUtils.isEmpty(password)) {
        // throw ExceptionHelper.prompt("密码不能为空");
        // }
        //
        // XtRy xtRy = xtRyService.getByDlzh(username);
        //
        // if (xtRy == null) {
        // throw ExceptionHelper.prompt("用户不存在");
        // }
        //
        // if (!Bool.Y.equals(xtRy.getSfqy())) {
        // throw ExceptionHelper.prompt("用户已禁用，请联系管理员");
        // }
        //
        // if (Bool.N.equals(xtRy.getSfsc())) {
        // throw ExceptionHelper.prompt("用户不存在，或者已经停用，请联系管理员");
        // }
        // if (!passwordEncoder.matches(password, xtRy.getDlmm())) {
        // throw ExceptionHelper.prompt("用户名或密码错误");
        // }
        //
        // String ryId = xtRy.getId();
        // String ryMc = xtRy.getMc();
        //
        // String[] jsIds = null;
        // {
        // List<String> jsIdList = xtJsService.findJsIdByRyId(ryId);
        // jsIds = CollectionUtil.toArray(jsIdList, String.class);
        // }
        // String[] gnIds = null;
        // {
        // List<String> gnIdList = new ArrayList<>();
        // gnIdList.addAll(xtGnService.findIdByRyId(ryId));
        // gnIdList.addAll(xtGnService.findIdByJsIds(jsIds));
        // gnIds = CollectionUtil.toArray(gnIdList, String.class);
        // }

        // Principal principal = new Principal();
        // principal.setRyId(ryId);
        // principal.setRyMc(ryMc);
        // principal.setDwId(xtRy.getDwId());
        // principal.setBmId(xtRy.getBmId());
        // principal.setJsIds(jsIds);
        // principal.setGnIds(gnIds);
        // return principal;
        return null;
    }
}
