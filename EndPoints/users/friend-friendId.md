# POST /users/friends/{friendId}
## Authenticated

## Path Parameters

| Name | Type | Description |
| --- | --- | --- |
| friendId | Long | 친구를 걸고 싶은 사람의 PK |

## Query Parameter

| Name | Type | Description |
| --- | --- | --- |
|  |  |  |

## Example

### Request

```
POST /users/friend/{friendId}
```

### RequestBody

```json

```

### Response (SUCCESS)

```json
{
    "response": {
        "message": "친구 신청을 걸었습니다"
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

### Response (SELF FRIEND NOT ALLOWED)

```json
{
    "response": {
        "error": "자신에게 친구 신청을 걸 수 없습니다"
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