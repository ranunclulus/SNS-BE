# POST /users/signup
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
POST /users/signup
```

### RequestBody

nullable: email, phone

```json

{
    "username": "ranunclulus",
    "password": "password",
    "email": "ranunclulus@gmail.com"
		"phone": "010-0000-0000"
}
```

### Response (SUCCESS)

```json
{
    "message": "회원 가입을 완료했습니다"
}
```

### Response (CONFLICT)

```json
{
    "message": "이미 존재하는 아이디입니다"
}
```