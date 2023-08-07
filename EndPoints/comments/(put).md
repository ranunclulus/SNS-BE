# PUT /articles/{articleId}/comments/{commentId}
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
PUT /articles/{articleId}/comments/{commentId}
```

### RequestBody

nullable: email, phone

```json
{
    "id": 1,
    "writer": "ranunclulus",
    "content": "반갑습니다!"
}
```

### Response (SUCCESS)

```json
{
    "response": {
        "message": "댓글이 수정되었습니다"
    }
}
```

### Response (WRITER NOT MATCH)

```json
{
    "response": {
        "error": "작성자가 아닙니다"
    }
}
```

### Response (CONTENT NULL)

```json
{
    "response": {
        "error": "내용이 비어 있습니다"
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

### Response (WRITER NOT FOUND)

```json
{
    "response": {
        "error": "아이디에 해당하는 사용자를 찾을 수 없습니다"
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