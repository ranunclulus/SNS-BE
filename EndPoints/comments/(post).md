# POST /articles/{articleId}/comments
## Authenticated

## Path Parameters

| Name | Type | Description |
| --- | --- | --- |
| articleId | Long | 게시글 PK |

## Query Parameter

| Name | Type | Description |
| --- | --- | --- |
|  |  |  |

## Example

### Request

```
POST /articles/{articleId}/comments
```

### RequestBody

nullable: email, phone

```json
{
    "id": 1,
    "writer": "ranunclulus",
    "content": "환영합니다!"
}
```

### Response (SUCCESS)

```json
{
    "response": {
        "message": "새로운 댓글이 등록되었습니다"
    }
}
```

### Response (WRITER NULL)

```json
{
    "response": {
        "error": "작성자가 비어 있습니다"
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

### Response (WRITER NOT FOUND)

```json
{
    "response": {
        "error": "아이디에 해당하는 사용자를 찾을 수 없습니다"
    }
}
```