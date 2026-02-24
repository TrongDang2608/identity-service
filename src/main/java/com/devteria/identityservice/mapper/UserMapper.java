package com.devteria.identityservice.mapper;

import com.devteria.identityservice.dto.request.UserCreationRequest;
import com.devteria.identityservice.dto.request.UserUpdateRequest;
import com.devteria.identityservice.dto.response.UserResponse;
import com.devteria.identityservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping; // Nhớ import cái này
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    // Lỗi 1: User có id nhưng Request không có -> Ignore id
    // User có roles (Set<Role>) nhưng Request có thể khác kiểu hoặc chưa xử lý -> Ignore roles
    @Mapping(target = "roles", ignore = true)
    User toUser(UserCreationRequest request);

    // Lỗi 2: UserResponse cần Set<String> nhưng User lại đưa Set<Role>
    // Tạm thời ignore để chạy được, sau này bạn sẽ viết hàm map riêng hoặc sửa DTO sau
    @Mapping(target = "roles", ignore = true)
    UserResponse toUserResponse(User user);

    // Lỗi 3: Update request không chứa id, username (vì không cho sửa), và roles đang bị lệch kiểu
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "username", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}