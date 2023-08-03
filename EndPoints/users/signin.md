# POST /users/signin
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
POST /users/signin
```

### RequestBody

nullable: email, phone

```json
{
    "username": "ranunclulus",
    "password": "password"
}
```

### Response (SUCCESS)

```json
{
    "response": {
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyYW51bmNsdWx1cyIsImlhdCI6MTY5MTA0MDM5MSwiZXhwIjoxNjkxMDQxMzkxfQ.7QrI1ho_DnqHMzDMoUSAwFSe2h5USvxCFlxY44I808JkaTJwcGYc8Nsnkfv7PU0eVAwxDjDPV4t8awG7bRvXRg"
    }
}
```

### Response (USERNAME NOT FOUND)

```json
{
    "response": {
        "error": "아이디에 해당하는 사용자를 찾을 수 없습니다"
    }
}
```

### Response (PASSWORD UNAUTHORIZED)

```json
{
    "response": {
        "error": "비밀번호가 일치하지 않습니다"
    }
}
```