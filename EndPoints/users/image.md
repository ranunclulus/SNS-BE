# PUT /users/image
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
        "message": "프로필 사진을 업로드했습니다"
    }
}
```