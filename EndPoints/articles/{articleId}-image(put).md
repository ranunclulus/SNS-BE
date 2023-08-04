# PUT /articles/{articleId}/image
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
    "photo": "form-data-file"
}
```

### Response (SUCCESS)

```json
{
    "response": {
        "message": "게시글 사진을 업로드했습니다"
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