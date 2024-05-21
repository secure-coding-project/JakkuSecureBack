package secure.project.secureProject.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import secure.project.secureProject.exception.ApiException;
import secure.project.secureProject.exception.ErrorDefine;

public class SecurityUtil {
    public static String getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new ApiException(ErrorDefine.ACCESS_DENIED);
        }
        return authentication.getName();
    }
}

