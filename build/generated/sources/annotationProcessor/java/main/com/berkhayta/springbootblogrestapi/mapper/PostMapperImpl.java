package com.berkhayta.springbootblogrestapi.mapper;

import com.berkhayta.springbootblogrestapi.dto.request.PostSaveRequestDto;
import com.berkhayta.springbootblogrestapi.dto.request.PostUpdateRequestDto;
import com.berkhayta.springbootblogrestapi.dto.response.PostListByCategoryIDResponseDto;
import com.berkhayta.springbootblogrestapi.dto.response.PostListByUserIDResponseDto;
import com.berkhayta.springbootblogrestapi.entity.Category;
import com.berkhayta.springbootblogrestapi.entity.Post;
import com.berkhayta.springbootblogrestapi.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-01T17:46:27+0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 21 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public Post postSaveRequestDtoToPost(PostSaveRequestDto postDto) {
        if ( postDto == null ) {
            return null;
        }

        Post.PostBuilder post = Post.builder();

        post.title( postDto.title() );
        post.content( postDto.content() );

        return post.build();
    }

    @Override
    public void updatePostFromDto(PostUpdateRequestDto dto, Post post) {
        if ( dto == null ) {
            return;
        }

        post.setTitle( dto.title() );
        post.setContent( dto.content() );
    }

    @Override
    public PostListByUserIDResponseDto postToPostListByUserIDResponseDto(Post post) {
        if ( post == null ) {
            return null;
        }

        Long categoryId = null;
        Long userId = null;
        Long id = null;
        String title = null;
        String content = null;

        categoryId = postCategoryId( post );
        userId = postAuthorId( post );
        id = post.getId();
        title = post.getTitle();
        content = post.getContent();

        PostListByUserIDResponseDto postListByUserIDResponseDto = new PostListByUserIDResponseDto( id, title, content, categoryId, userId );

        return postListByUserIDResponseDto;
    }

    @Override
    public PostListByCategoryIDResponseDto postToPostListByCategoryIDResponseDto(Post post) {
        if ( post == null ) {
            return null;
        }

        Long categoryId = null;
        Long userId = null;
        Long id = null;
        String title = null;
        String content = null;

        categoryId = postCategoryId( post );
        userId = postAuthorId( post );
        id = post.getId();
        title = post.getTitle();
        content = post.getContent();

        PostListByCategoryIDResponseDto postListByCategoryIDResponseDto = new PostListByCategoryIDResponseDto( id, title, content, categoryId, userId );

        return postListByCategoryIDResponseDto;
    }

    private Long postCategoryId(Post post) {
        if ( post == null ) {
            return null;
        }
        Category category = post.getCategory();
        if ( category == null ) {
            return null;
        }
        Long id = category.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long postAuthorId(Post post) {
        if ( post == null ) {
            return null;
        }
        User author = post.getAuthor();
        if ( author == null ) {
            return null;
        }
        Long id = author.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
