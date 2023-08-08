# POST /articles
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
POST /articles
```

### RequestBody


```json
{
    "title": "할 수 있다",
    "writer": "ranunclulus",
    "content": "첫 게시글!"
}
```

### Response (SUCCESS)

```json
{
    "response": {
        "message": "새로운 게시글이 등록되었습니다"
    }
}
```

### Response (TITLE NULL)

```json
{
    "response": {
        "error": "제목이 비어 있습니다"
    }
}
```

### Response (WRITER NULL)

```json
{
    "response": {
        "error": "작성자가 비어 있습니다"
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
### Response (WRITER NOT MATCH)

```json
{
    "response": {
        "error": "작성자가 일치하지 않습니다"
    }
}
```