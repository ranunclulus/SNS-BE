# PUT /users/friends
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
PUT /users/friends
```

### RequestBody

```json
{
    "fromUser": "ranunclulus",
    "status": "거절"
}
```

### Response (SUCCESS)

```json
{
    "response": {
        "message": "친구 요청을 수락했습니다"
    }
}

{
    "response": {
        "message": "친구 요청을 거절했습니다"
    }
}
```

### Response (USER NOT FOUND)

```json
{
    "response": {
        "error": "아이디에 해당하는 사용자를 찾을 수 없습니다"
    }
}
```

### Response (FRIEND NOT FOUND)

```json
{
    "response": {
        "error": "친구 요청 내용을 찾을 수 없습니다"
    }
}
```

### Response (ALREADY DECIDED)

```json
{
    "response": {
        "error": "이미 처리된 친구 요청입니다"
    }
}
```

### Response (DELETE USER)

```
{
    "response": {
        "error": "탈퇴한 사용자입니다"
    }
}
```

### Response (ALREADY EXSIST)

```json
{
    "response": {
        "error": "이미 친구 신청을 걸었습니다"
    }
}
```