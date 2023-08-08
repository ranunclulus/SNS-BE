# Project_2_JoeHuisu
## Development Environment

</div>
<div align="center">
	<img src="https://img.shields.io/badge/Java-007396?style=flat&logo=Conda-Forge&logoColor=white" />
	
<img src="https://img.shields.io/badge/Spring-6DB33F?style=flat&logo=Spring&logoColor=white" />
<img src="https://img.shields.io/badge/GitHub-181717?style=flat&logo=GitHub&logoColor=white" />
</div>

## EndPoint
- ---
### Users
- [`POST /users/signup`](/EndPoints/users/signup.md)
- [`POST /users/signin`](/EndPoints/users/signin.md)
- [`PUT /users/image`](/EndPoints/users/image.md)
- [`GET /users`](/EndPoints/users/(get).md)
- [`PUT /users/follow/{followId}`](/EndPoints/users/follow-followId.md)
- ---

### Articles
- [`POST /articles`](/EndPoints/articles/articles(post).md)
- [`GET /articles`](/EndPoints/articles/articles(get).md)
- [`GET /articles/{articleId}`](/EndPoints/articles/{articleId}(get).md)
- [`GET /articles/follow`](/EndPoints/articles/follow.md)
- [`GET /articles/friend`](/EndPoints/articles/friend.md)
- [`PUT /articles/{articleId}/image`](/EndPoints/articles/{articleId}-image(put).md)
- [`PUT /articles/{articleId}/likes`](/EndPoints/articles/{articleId}-likes.md)
- [`DELETE /articles/{articleId}/image/{imageId}`](/EndPoints/articles/{articleId}-image-{imageId}.md)

- ---
### Comment
- [`POST /articles/{articleId}/comments`](/EndPoints/comments/(post).md)
- [`PUT /articles/{articleId}/comments/{commentId}`](/EndPoints/comments/(put).md)
- [`DELETE /articles/{articleId}/comments/{commentId}`](/EndPoints/comments/{commentId}(delete).md)
- [`GET /articles/{articleId}`](/EndPoints/articles/{articleId}(get).md)
- [`PUT /articles/{articleId}/image`](/EndPoints/articles/{articleId}-image(put).md)
