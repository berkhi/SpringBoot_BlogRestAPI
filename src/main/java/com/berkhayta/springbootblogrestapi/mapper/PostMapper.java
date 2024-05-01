package com.berkhayta.springbootblogrestapi.mapper;

import com.berkhayta.springbootblogrestapi.dto.request.PostSaveRequestDto;
import com.berkhayta.springbootblogrestapi.dto.request.PostUpdateRequestDto;
import com.berkhayta.springbootblogrestapi.dto.response.PostListAllResponseDto;
import com.berkhayta.springbootblogrestapi.dto.response.PostListByCategoryIDResponseDto;
import com.berkhayta.springbootblogrestapi.dto.response.PostListByUserIDResponseDto;
import com.berkhayta.springbootblogrestapi.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    PostMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(PostMapper.class);

    Post postSaveRequestDtoToPost(PostSaveRequestDto postDto);

    // Postları gösterirken category ve yazar ID lerini gösterebilmek için Post nesnesinden
    // categoryId ve userId bilgilerini de alacak şekilde bir metod oluşturuldu.
    // findAllPostDto() ve findPostById() metodların kullandık
    default PostListAllResponseDto postFindAllResponseDto(Post post) {
        if (post == null) {
            return null;
        }
        return new PostListAllResponseDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCategory() != null ? post.getCategory().getId() : null,
                post.getAuthor() != null ? post.getAuthor().getId() : null
        );
    }

    void updatePostFromDto(PostUpdateRequestDto dto, @MappingTarget Post post);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "author.id", target = "userId")
    PostListByUserIDResponseDto postToPostListByUserIDResponseDto(Post post);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "author.id", target = "userId")
    PostListByCategoryIDResponseDto postToPostListByCategoryIDResponseDto(Post post);
}
