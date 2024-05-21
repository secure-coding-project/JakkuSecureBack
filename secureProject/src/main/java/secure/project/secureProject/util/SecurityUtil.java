package secure.project.secureProject.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import secure.project.secureProject.exception.ApiException;
import secure.project.secureProject.exception.ErrorDefine;

@Component
@RequiredArgsConstructor
public class SecurityUtil {
    public String getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getName() == null) {
            throw new ApiException(ErrorDefine.ACCESS_DENIED);
        }
        return authentication.getName();
    }
}

