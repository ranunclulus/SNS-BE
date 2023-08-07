# PUT /users/follow/{followId}
## Authenticated

## Path Parameters

| Name | Type | Description |
| --- | --- | --- |
| followId | Long | 팔로우를 걸고 싶은 사람의 PK |

## Query Parameter

| Name | Type | Description |
| --- | --- | --- |
|  |  |  |

## Example

### Request

```
PUT /users/follow/{followId}
```

### RequestBody

```json

```

### Response (SUCCESS)

```json
{
    "response": {
        "message": "팔로우를 걸었습니다"
    }
}

{
    "response": {
        "message": "팔로우를 취소했습니다"
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

### Response (SELF FOLLOW NOT ALLOWED)

```json
{
    "response": {
        "error": "자신을 팔로우할 수 없습니다"
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