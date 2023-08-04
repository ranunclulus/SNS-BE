# DELETE /articles/{articleId}/image/{imageId}
## Authenticated

## Path Parameters

| Name | Type | Description |
| --- | --- | --- |
| articleId | Long | Article PK |
| imageId | Long | Image PK |

## Query Parameter

| Name | Type | Description |
| --- | --- | --- |
|  |  |  |

## Example

### Request

```
DELETE /articles/{articleId}/image/{imageId}
```

### RequestBody

```json

```

### Response (SUCCESS)

```json
{
    "response": {
        "message": "게시글 사진을 삭제했습니다"
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

### Response (ARTICLE NOT FOUND)

```json
{
    "response": {
        "error": "이미지를 찾을 수 없습니다"
    }
}
```

### Response (IMAGE AND ARTICLE NOT MATCHED)

```json
{
    "response": {
        "error": "해당 게시글의 이미지가 아닙니다"
    }
}
```