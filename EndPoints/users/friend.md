# GET /users/friend
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
GET /users/friend
```

### RequestBody

```json

```

### Response (SUCCESS)

```json
{
    "response": {
        "result": "친구 요청이 없습니다",
        "message": "친구 요청 목록을 불러오는 데 성공했습니다"
    }
}

{
    "response": {
        "result": [
            {
                "id": 1,
                "fromUser": "ranunclulus",
                "toUser": "ranunclulu",
                "status": "요청"
            }
        ],
        "message": "친구 요청 목록을 불러오는 데 성공했습니다"
    }
}
```