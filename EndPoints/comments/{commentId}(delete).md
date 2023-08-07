# DELETE /articles/{articleId}/comments/{commentId}
## Authenticated

## Path Parameters

| Name | Type | Description |
| --- | --- | --- |
| articleId | Long | 게시글 PK |
| commentId | Long | 댓글 PK |

## Query Parameter

| Name | Type | Description |
| --- | --- | --- |
|  |  |  |

## Example

### Request

```
DELETE /articles/{articleId}/comments/{commentId}
```

### RequestBody

nullable: email, phone

```json

```

### Response (SUCCESS)

```json
{
    "response": {
        "message": "댓글이 삭제되었습니다"
    }
}
```

### Response (ARTICLE NOT FOUND)

```json
{
    "response": {
        "error": "게시글을 찾을 수 없습니다"
    }
}
```

### Response (COMMENT NOT FOUND)

```json
{
    "response": {
        "error": "댓글을 찾을 수 없습니다"
    }
}
```

### Response (DELETED COMMENT)

```json
{
    "response": {
        "error": "삭제된 게시글입니다"
    }
}
```