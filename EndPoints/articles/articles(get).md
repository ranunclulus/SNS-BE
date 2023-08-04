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

```

### Response (SUCCESS)
```json
{
    "response": {
        "result": [
            {
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
            {
                "id": 2,
                "writer": "ranunclulus",
                "title": "할 수 있다",
                "content": "첫 게시글!",
                "draft": false,
                "imageUrl": [
                    "/media/articleImages/default.png"
                ],
                "deletedAt": null,
                "createdAt": "2023-08-04T15:34:01.448"
            },
            {
                "id": 3,
                "writer": "ranunclulus",
                "title": "할 수 있다",
                "content": "첫 게시글!",
                "draft": false,
                "imageUrl": [
                    "/media/articleImages/default.png"
                ],
                "deletedAt": null,
                "createdAt": "2023-08-04T15:34:01.874"
            }
        ],
        "message": "게시글 목록을 불러오는 데 성공했습니다"
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