# GET /articles
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
POST /users/image
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
        "message": "게시글을 불러오는 데 성공했습니다",
        "result": [
            {
                "id": 1,
                "writer": "ranunclulus",
                "title": "할 수 있다",
                "content": "첫 게시글!",
                "draft": false,
                "deletedAt": null,
                "createdAt": "2023-08-04T13:49:39.621"
            },
            {
                "id": 2,
                "writer": "ranunclulus",
                "title": "할 수 있다",
                "content": "첫 게시글!",
                "draft": false,
                "deletedAt": null,
                "createdAt": "2023-08-04T13:49:40.77"
            },
            {
                "id": 3,
                "writer": "ranunclulus",
                "title": "할 수 있다",
                "content": "첫 게시글!",
                "draft": false,
                "deletedAt": null,
                "createdAt": "2023-08-04T13:49:42.338"
            },
            {
                "id": 4,
                "writer": "ranunclulus",
                "title": "할 수 있다",
                "content": "첫 게시글!",
                "draft": false,
                "deletedAt": null,
                "createdAt": "2023-08-04T13:49:43.307"
            }
        ],
    }
}
```

### Response (ARTICLE NOT FOUND)

```json
{
    "response": {
        "message": "프로필 사진을 업로드했습니다"
    }
}
```