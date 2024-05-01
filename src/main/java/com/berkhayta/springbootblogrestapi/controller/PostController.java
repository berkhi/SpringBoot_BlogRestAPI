package com.berkhayta.springbootblogrestapi.controller;

import com.berkhayta.springbootblogrestapi.dto.request.PostSaveRequestDto;
import com.berkhayta.springbootblogrestapi.dto.request.PostUpdateRequestDto;
import com.berkhayta.springbootblogrestapi.dto.response.PostListAllResponseDto;
import com.berkhayta.springbootblogrestapi.dto.response.PostListByCategoryIDResponseDto;
import com.berkhayta.springbootblogrestapi.dto.response.PostListByUserIDResponseDto;
import com.berkhayta.springbootblogrestapi.entity.Post;
import com.berkhayta.springbootblogrestapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.berkhayta.springbootblogrestapi.constant.EndPoints.*;

@RestController
@RequestMapping(ROOT + POST)
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // PostSaveRequestDto tipinde bir nesneyi alıp, bu nesneyi bir Post nesnesine dönüştürüp kaydeder
    @PostMapping("/posts")
    public ResponseEntity<?> savePostDto(@RequestBody PostSaveRequestDto dto) {
        try {
            // DTO'yu PostService'deki savePost metoduna gönderir ve kaydedilen Post nesnesini alır.
            Post savedPost = postService.savePost(dto);
            // Kaydedilen Post nesnesinden gerekli bilgileri çekerek bir Map'e yerleştirir.
            Map<String, Object> response = new HashMap<>();
            response.put("id", savedPost.getId());
            response.put("title", savedPost.getTitle());
            response.put("content", savedPost.getContent());
            response.put("publishedAt", savedPost.getPublishedAt());
            response.put("userId", savedPost.getAuthor() != null ? savedPost.getAuthor().getId() : null);
            response.put("categoryId", savedPost.getCategory() != null ? savedPost.getCategory().getId() : null);

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error occurred while saving post: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(("/posts"))
    public ResponseEntity<List<PostListAllResponseDto>> findAllDto() {
        return ResponseEntity.ok(postService.findAllPostDto());
    }

    // get isteği ile verilen id ye sahip Post'u arar, bulur ve onu PostListAllResponseDto tipinde döner
    @GetMapping("/{postId}")
    public ResponseEntity<PostListAllResponseDto> getPostById(@PathVariable("postId") Long postId) {
        PostListAllResponseDto postDto = postService.findPostById(postId);
        return ResponseEntity.ok(postDto);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostUpdateRequestDto> updatePost(@PathVariable("postId") Long postId, @RequestBody PostUpdateRequestDto dto) {
        PostUpdateRequestDto updatedPost = postService.updatePost(postId, dto);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        try {
            postService.deletePostById(postId);
            return ResponseEntity.ok().build();  //Başarılı silme işlemi
        } catch (Exception e) {
            return ResponseEntity.notFound().build();  //Post bulunamadı veya silinemedi
        }
    }

    //categoryId ile ilişkili tüm postları bulur ve bunları PostListByUserIDResponseDto listesi olarak döndürür.
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostListByUserIDResponseDto>> getPostsByUserId(@PathVariable Long userId) {
        List<PostListByUserIDResponseDto> posts = postService.findPostsByUserId(userId);
        return ResponseEntity.ok(posts);
    }

    //categoryId ile ilişkili tüm postları bulur ve bunları PostListByCategoryIDResponseDto listesi olarak döndürür.
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostListByCategoryIDResponseDto>> getPostsByCategoryId(@PathVariable Long categoryId) {
        List<PostListByCategoryIDResponseDto> posts = postService.findPostsByCategoryId(categoryId);
        return ResponseEntity.ok(posts);
    }

    // -------------- LISTING AND FILTERING ---------------------

    //sortBy parametresi ile yayın tarihine göre sıralama seçeneği (published_at parametresi ile).
    @GetMapping("/sortBy/{sortBy}")
    public ResponseEntity<List<PostListAllResponseDto>> getPosts(@RequestParam(required = false) String sortBy) {
        List<PostListAllResponseDto> posts = postService.findAllPosts(sortBy);
        return ResponseEntity.ok(posts);
    }

    //Yazıları belirli bir kategoriye göre filtreleme seçeneği (category_id parametresi ile).
    // (BU SORU getPostsByCategoryId METODUNDA CEVAPLANMIŞTIR.)

    //Kullanıcının yazılarını listeleme seçeneği (user_id parametresi ile).
    // (BU SORU getPostsByUserId METODUNDA CEVAPLANMIŞTIR.)

    //Kategorileri isme göre arama seçeneği (name parametresi ile).
    // (BU SORU getPostsByCategoryName METODUNDA CEVAPLANMIŞTIR.)
}
