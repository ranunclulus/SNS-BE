# PUT /articles/{articleId}/likes
## Authenticated

## Path Parameters

| Name | Type | Description |
| --- | --- | --- |
| articleId | Long | Article PK |

## Query Parameter

| Name | Type | Description |
| --- | --- | --- |
|  |  |  |

## Example

### Request

```
PUT /articles/{articleId}/image
```

### RequestBody

```json
{
    "writer": "ranunclulu"
}
```

### Response (SUCCESS)

```json
{
    "response": {
        "message": "게시글에 좋아요를 눌렀습니다"
    }
}

{
    "response": {
        "message": "게시글에 좋아요를 취소했습니다"
    }
}
```

### Response (DELETED ARTICLE)

```json
{
    "response": {
        "message": "삭제된 게시글입니다"
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

### Response (WRITER NOT FOUND)

```json
{
    "response": {
        "error": "아이디에 해당하는 사용자를 찾을 수 없습니다"
    }
}
```

### Response (SELF LIKE NOT ALLOWED

```json
{
    "response": {
        "error": "자신의 게시글에 좋아요를 남길 수 없습니다"
    }
}
```