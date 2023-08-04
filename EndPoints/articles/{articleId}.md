# GET /articles/{articleId}
## Authenticated

## Path Parameters

| Name | Type | Description |
| --- | --- | --- |
|  |  |  |

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

nullable: email, phone

```json
{
    "photo": "form-data-file"
}
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
            "deletedAt": null,
            "createdAt": "2023-08-04T14:19:55.853"
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