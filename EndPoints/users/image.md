# PUT /users/image
## Authenticated

## Path Parameters

| Name  | Type           | Description   |
|-------|----------------|---------------|
| photo | form-data-file | 올리고 싶은 프로필 파일 |

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
        "message": "프로필 사진을 업로드했습니다"
    }
}
```