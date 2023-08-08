# GET /articles/follow
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
GET /articles/follow
```

### RequestBody

```json

```

### Response (SUCCESS)

```json
{
    "response": {
        "result": [
            {
                "id": 5,
                "writer": "ranunclulu",
                "title": "할 수 있다",
                "content": "첫 게시글!",
                "draft": false,
                "imageUrl": [],
                "deletedAt": null,
                "createdAt": "2023-08-08T15:15:05.363"
            },
            {
                "id": 4,
                "writer": "ranunclulu",
                "title": "할 수 있다",
                "content": "첫 게시글!",
                "draft": false,
                "imageUrl": [],
                "deletedAt": null,
                "createdAt": "2023-08-08T15:15:05.04"
            },
            {
                "id": 3,
                "writer": "ranunclulu",
                "title": "할 수 있다",
                "content": "첫 게시글!",
                "draft": false,
                "imageUrl": [],
                "deletedAt": null,
                "createdAt": "2023-08-08T15:15:04.678"
            },
            {
                "id": 2,
                "writer": "ranunclulu",
                "title": "할 수 있다",
                "content": "첫 게시글!",
                "draft": false,
                "imageUrl": [],
                "deletedAt": null,
                "createdAt": "2023-08-08T15:15:04.305"
            },
            {
                "id": 1,
                "writer": "ranunclulu",
                "title": "할 수 있다",
                "content": "첫 게시글!",
                "draft": false,
                "imageUrl": [],
                "deletedAt": null,
                "createdAt": "2023-08-08T15:15:03.656"
            }
        ],
        "message": "게시글 목록을 불러오는 데 성공했습니다"
    }
}

{
    "response": {
        "message": "작성된 게시글이 없습니다"
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