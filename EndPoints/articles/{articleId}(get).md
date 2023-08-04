# GET /articles/{articleId}
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
GET /articles/{articleId}
```

### RequestBody

```json

```

### Response (SUCCESS)
```json
{
    "response": {
        "result": {
            "id": 1,
            "writer": "ranunclulus",
            "title": "할 수 있다",
            "content": "첫 게시글!",
            "draft": false,
            "imageUrl": [
                "/media/articleImages/default.png"
            ],
            "deletedAt": null,
            "createdAt": "2023-08-04T15:34:00.91"
        },
        "message": "게시글을 불러오는 데 성공했습니다"
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