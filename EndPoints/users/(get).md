# GET /users
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
GET /users
```

### RequestBody

```json

```

### Response (SUCCESS)

```json
{
    "response": {
        "user info": {
            "username": "ranunclulus",
            "password": "$2a$10$U3MAESFAV4K8EM03LEYbb.wCFuWyhsVhZqIo/R6kW7YCLLKHSids2",
            "email": "ranunclulus@gmail.com",
            "phone": "010-0000-0000",
            "enabled": true,
            "accountNonExpired": false,
            "accountNonLocked": true,
            "credentialsNonExpired": false,
            "authorities": null
        },
        "message": "사용자 정보를 조회했습니다"
    }
}
```