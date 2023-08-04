# DELETE /articles/{articleId}
## Authenticated

## Path Parameters

| Name      | Type | Description |
|-----------|------|-------------|
| articleId | Long | Article PK  |

## Query Parameter

| Name | Type | Description |
| --- | --- | --- |
|  |  |  |

## Example

### Request

```
DELETE /articles/{articleId}
```

### RequestBody

nullable: email, phone

```json

```

### Response (SUCCESS)

```json
{
    "response": {
        "message": "게시글을 성공적으로 삭제했습니다"
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