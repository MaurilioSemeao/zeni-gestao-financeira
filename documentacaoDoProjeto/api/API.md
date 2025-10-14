---
title: Zeni - API de Gestão Financeira v1.0.0
language_tabs:
  - shell: Shell
  - http: HTTP
  - javascript: JavaScript
  - ruby: Ruby
  - python: Python
  - php: PHP
  - java: Java
  - go: Go
toc_footers: []
includes: []
search: true
highlight_theme: darkula
headingLevel: 2

---

<!-- Generator: Widdershins v4.0.1 -->

<h1 id="zeni-api-de-gest-o-financeira">Zeni - API de Gestão Financeira v1.0.0</h1>

> Scroll down for code samples, example requests and responses. Select a language for code samples from the tabs above or the mobile navigation menu.

API REST para o sistema de gestão financeira pessoal Zeni.

Base URLs:

* <a href="https://localhost:8080">https://localhost:8080</a>

* <a href="http://localhost:8080">http://localhost:8080</a>

# Authentication

- HTTP Authentication, scheme: bearer 

<h1 id="zeni-api-de-gest-o-financeira-autentica-o">Autenticação</h1>

## post__auth_cadastrar

> Code samples

```shell
# You can also use wget
curl -X POST https://localhost:8080/auth/cadastrar \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json'

```

```http
POST https://localhost:8080/auth/cadastrar HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json

```

```javascript
const inputBody = '{
  "nome": "Usuário Exemplo",
  "email": "usuario@example.com",
  "senha": "senhaForte123"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'application/json'
};

fetch('https://localhost:8080/auth/cadastrar',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => 'application/json'
}

result = RestClient.post 'https://localhost:8080/auth/cadastrar',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json'
}

r = requests.post('https://localhost:8080/auth/cadastrar', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => 'application/json',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','https://localhost:8080/auth/cadastrar', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/auth/cadastrar");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"application/json"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "https://localhost:8080/auth/cadastrar", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /auth/cadastrar`

*(HU-01) Cria uma nova conta de usuário*

> Body parameter

```json
{
  "nome": "Usuário Exemplo",
  "email": "usuario@example.com",
  "senha": "senhaForte123"
}
```

<h3 id="post__auth_cadastrar-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[CadastroRequest](#schemacadastrorequest)|true|none|

> Example responses

> 201 Response

```json
{
  "id": 0,
  "nome": "string",
  "email": "user@example.com",
  "tipoUsuario": "PADRAO"
}
```

<h3 id="post__auth_cadastrar-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Usuário criado com sucesso.|[UsuarioResponse](#schemausuarioresponse)|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|Dados de cadastro inválidos (ex. e-mail em formato incorreto, senha fraca).|None|
|409|[Conflict](https://tools.ietf.org/html/rfc7231#section-6.5.8)|Conflito. O e-mail fornecido já está em uso.|None|

<aside class="success">
This operation does not require authentication
</aside>

## post__auth_login

> Code samples

```shell
# You can also use wget
curl -X POST https://localhost:8080/auth/login \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json'

```

```http
POST https://localhost:8080/auth/login HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json

```

```javascript
const inputBody = '{
  "email": "usuario@example.com",
  "senha": "senhaForte123"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'application/json'
};

fetch('https://localhost:8080/auth/login',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => 'application/json'
}

result = RestClient.post 'https://localhost:8080/auth/login',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json'
}

r = requests.post('https://localhost:8080/auth/login', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => 'application/json',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','https://localhost:8080/auth/login', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/auth/login");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"application/json"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "https://localhost:8080/auth/login", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /auth/login`

*(HU-02) Autentica um usuário*

> Body parameter

```json
{
  "email": "usuario@example.com",
  "senha": "senhaForte123"
}
```

<h3 id="post__auth_login-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[LoginRequest](#schemaloginrequest)|true|none|

> Example responses

> 200 Response

```json
{
  "token": "string",
  "usuario": {
    "id": 0,
    "nome": "string",
    "email": "user@example.com",
    "tipoUsuario": "PADRAO"
  }
}
```

<h3 id="post__auth_login-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|Autenticação bem-sucedida.|[LoginResponse](#schemaloginresponse)|
|401|[Unauthorized](https://tools.ietf.org/html/rfc7235#section-3.1)|Não autorizado. Credenciais inválidas.|None|

<aside class="success">
This operation does not require authentication
</aside>

<h1 id="zeni-api-de-gest-o-financeira-usu-rios">Usuários</h1>

## get__usuarios_me

> Code samples

```shell
# You can also use wget
curl -X GET https://localhost:8080/usuarios/me \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET https://localhost:8080/usuarios/me HTTP/1.1
Host: localhost:8080
Accept: application/json

```

```javascript

const headers = {
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://localhost:8080/usuarios/me',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'https://localhost:8080/usuarios/me',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('https://localhost:8080/usuarios/me', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','https://localhost:8080/usuarios/me', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/usuarios/me");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "https://localhost:8080/usuarios/me", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /usuarios/me`

*Retorna o perfil do usuário logado*

> Example responses

> 200 Response

```json
{
  "id": 0,
  "nome": "string",
  "email": "user@example.com",
  "tipoUsuario": "PADRAO"
}
```

<h3 id="get__usuarios_me-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|Perfil do usuário retornado com sucesso.|[UsuarioResponse](#schemausuarioresponse)|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

<h1 id="zeni-api-de-gest-o-financeira-transa-es">Transações</h1>

## get__transacoes

> Code samples

```shell
# You can also use wget
curl -X GET https://localhost:8080/transacoes \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET https://localhost:8080/transacoes HTTP/1.1
Host: localhost:8080
Accept: application/json

```

```javascript

const headers = {
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://localhost:8080/transacoes',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'https://localhost:8080/transacoes',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('https://localhost:8080/transacoes', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','https://localhost:8080/transacoes', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/transacoes");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "https://localhost:8080/transacoes", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /transacoes`

*Lista as transações do usuário*

> Example responses

> 200 Response

```json
[
  {
    "id": 0,
    "descricao": "string",
    "valor": 0.1,
    "data": "2019-08-24",
    "tipo": "string",
    "categoriaNome": "string"
  }
]
```

<h3 id="get__transacoes-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|Lista de transações retornada com sucesso.|Inline|

<h3 id="get__transacoes-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|*anonymous*|[[TransacaoResponse](#schematransacaoresponse)]|false|none|none|
|» id|integer(int64)|false|none|none|
|» descricao|string|false|none|none|
|» valor|number(double)|false|none|none|
|» data|string(date)|false|none|none|
|» tipo|string|false|none|none|
|» categoriaNome|string|false|none|none|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## post__transacoes

> Code samples

```shell
# You can also use wget
curl -X POST https://localhost:8080/transacoes \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
POST https://localhost:8080/transacoes HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json

```

```javascript
const inputBody = '{
  "descricao": "Almoço no restaurante",
  "valor": 45.5,
  "data": "2025-10-13",
  "tipo": "RECEITA",
  "categoriaId": 0,
  "contaId": 0,
  "cartaoId": 0
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://localhost:8080/transacoes',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.post 'https://localhost:8080/transacoes',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.post('https://localhost:8080/transacoes', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','https://localhost:8080/transacoes', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/transacoes");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "https://localhost:8080/transacoes", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /transacoes`

*(HU-03) Registra uma nova transação*

> Body parameter

```json
{
  "descricao": "Almoço no restaurante",
  "valor": 45.5,
  "data": "2025-10-13",
  "tipo": "RECEITA",
  "categoriaId": 0,
  "contaId": 0,
  "cartaoId": 0
}
```

<h3 id="post__transacoes-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[TransacaoRequest](#schematransacaorequest)|true|none|

> Example responses

> 201 Response

```json
{
  "id": 0,
  "descricao": "string",
  "valor": 0.1,
  "data": "2019-08-24",
  "tipo": "string",
  "categoriaNome": "string"
}
```

<h3 id="post__transacoes-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Transação criada com sucesso.|[TransacaoResponse](#schematransacaoresponse)|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## put__transacoes_{id}

> Code samples

```shell
# You can also use wget
curl -X PUT https://localhost:8080/transacoes/{id} \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
PUT https://localhost:8080/transacoes/{id} HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json

```

```javascript
const inputBody = '{
  "descricao": "Almoço no restaurante",
  "valor": 45.5,
  "data": "2025-10-13",
  "tipo": "RECEITA",
  "categoriaId": 0,
  "contaId": 0,
  "cartaoId": 0
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://localhost:8080/transacoes/{id}',
{
  method: 'PUT',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.put 'https://localhost:8080/transacoes/{id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.put('https://localhost:8080/transacoes/{id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('PUT','https://localhost:8080/transacoes/{id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/transacoes/{id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("PUT");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("PUT", "https://localhost:8080/transacoes/{id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`PUT /transacoes/{id}`

*Atualiza uma transação*

> Body parameter

```json
{
  "descricao": "Almoço no restaurante",
  "valor": 45.5,
  "data": "2025-10-13",
  "tipo": "RECEITA",
  "categoriaId": 0,
  "contaId": 0,
  "cartaoId": 0
}
```

<h3 id="put__transacoes_{id}-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer|true|none|
|body|body|[TransacaoRequest](#schematransacaorequest)|true|none|

> Example responses

> 200 Response

```json
{
  "id": 0,
  "descricao": "string",
  "valor": 0.1,
  "data": "2019-08-24",
  "tipo": "string",
  "categoriaNome": "string"
}
```

<h3 id="put__transacoes_{id}-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|Transação atualizada com sucesso.|[TransacaoResponse](#schematransacaoresponse)|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Transação não encontrada.|None|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## delete__transacoes_{id}

> Code samples

```shell
# You can also use wget
curl -X DELETE https://localhost:8080/transacoes/{id} \
  -H 'Authorization: Bearer {access-token}'

```

```http
DELETE https://localhost:8080/transacoes/{id} HTTP/1.1
Host: localhost:8080

```

```javascript

const headers = {
  'Authorization':'Bearer {access-token}'
};

fetch('https://localhost:8080/transacoes/{id}',
{
  method: 'DELETE',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.delete 'https://localhost:8080/transacoes/{id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Authorization': 'Bearer {access-token}'
}

r = requests.delete('https://localhost:8080/transacoes/{id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('DELETE','https://localhost:8080/transacoes/{id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/transacoes/{id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("DELETE");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("DELETE", "https://localhost:8080/transacoes/{id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`DELETE /transacoes/{id}`

*Exclui uma transação*

<h3 id="delete__transacoes_{id}-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer|true|none|

<h3 id="delete__transacoes_{id}-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|Transação excluída com sucesso.|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Transação não encontrada.|None|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

<h1 id="zeni-api-de-gest-o-financeira-categorias">Categorias</h1>

## get__categorias

> Code samples

```shell
# You can also use wget
curl -X GET https://localhost:8080/categorias \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET https://localhost:8080/categorias HTTP/1.1
Host: localhost:8080
Accept: application/json

```

```javascript

const headers = {
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://localhost:8080/categorias',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'https://localhost:8080/categorias',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('https://localhost:8080/categorias', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','https://localhost:8080/categorias', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/categorias");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "https://localhost:8080/categorias", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /categorias`

*Lista as categorias do usuário*

> Example responses

> 200 Response

```json
[
  {
    "id": 0,
    "nome": "string",
    "limiteGasto": 0.1
  }
]
```

<h3 id="get__categorias-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|Lista de categorias retornada com sucesso.|Inline|

<h3 id="get__categorias-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|*anonymous*|[[CategoriaResponse](#schemacategoriaresponse)]|false|none|none|
|» id|integer(int64)|false|none|none|
|» nome|string|false|none|none|
|» limiteGasto|number(double)|false|none|none|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## post__categorias

> Code samples

```shell
# You can also use wget
curl -X POST https://localhost:8080/categorias \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
POST https://localhost:8080/categorias HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json

```

```javascript
const inputBody = '{
  "nome": "Lazer",
  "limiteGasto": 500
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://localhost:8080/categorias',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.post 'https://localhost:8080/categorias',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.post('https://localhost:8080/categorias', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','https://localhost:8080/categorias', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/categorias");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "https://localhost:8080/categorias", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /categorias`

*(HU-04) Cria uma nova categoria*

> Body parameter

```json
{
  "nome": "Lazer",
  "limiteGasto": 500
}
```

<h3 id="post__categorias-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[CategoriaRequest](#schemacategoriarequest)|true|none|

> Example responses

> 201 Response

```json
{
  "id": 0,
  "nome": "string",
  "limiteGasto": 0.1
}
```

<h3 id="post__categorias-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Categoria criada com sucesso.|[CategoriaResponse](#schemacategoriaresponse)|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## put__categorias_{id}

> Code samples

```shell
# You can also use wget
curl -X PUT https://localhost:8080/categorias/{id} \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
PUT https://localhost:8080/categorias/{id} HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json

```

```javascript
const inputBody = '{
  "nome": "Lazer",
  "limiteGasto": 500
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://localhost:8080/categorias/{id}',
{
  method: 'PUT',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.put 'https://localhost:8080/categorias/{id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.put('https://localhost:8080/categorias/{id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('PUT','https://localhost:8080/categorias/{id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/categorias/{id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("PUT");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("PUT", "https://localhost:8080/categorias/{id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`PUT /categorias/{id}`

*(HU-08) Atualiza uma categoria*

> Body parameter

```json
{
  "nome": "Lazer",
  "limiteGasto": 500
}
```

<h3 id="put__categorias_{id}-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer|true|none|
|body|body|[CategoriaRequest](#schemacategoriarequest)|true|none|

> Example responses

> 200 Response

```json
{
  "id": 0,
  "nome": "string",
  "limiteGasto": 0.1
}
```

<h3 id="put__categorias_{id}-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|Categoria atualizada com sucesso.|[CategoriaResponse](#schemacategoriaresponse)|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Categoria não encontrada.|None|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## delete__categorias_{id}

> Code samples

```shell
# You can also use wget
curl -X DELETE https://localhost:8080/categorias/{id} \
  -H 'Authorization: Bearer {access-token}'

```

```http
DELETE https://localhost:8080/categorias/{id} HTTP/1.1
Host: localhost:8080

```

```javascript

const headers = {
  'Authorization':'Bearer {access-token}'
};

fetch('https://localhost:8080/categorias/{id}',
{
  method: 'DELETE',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.delete 'https://localhost:8080/categorias/{id}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Authorization': 'Bearer {access-token}'
}

r = requests.delete('https://localhost:8080/categorias/{id}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('DELETE','https://localhost:8080/categorias/{id}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/categorias/{id}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("DELETE");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("DELETE", "https://localhost:8080/categorias/{id}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`DELETE /categorias/{id}`

*Exclui uma categoria*

<h3 id="delete__categorias_{id}-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|id|path|integer|true|none|

<h3 id="delete__categorias_{id}-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|Categoria excluída com sucesso.|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Categoria não encontrada.|None|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

<h1 id="zeni-api-de-gest-o-financeira-contas">Contas</h1>

## get__contas

> Code samples

```shell
# You can also use wget
curl -X GET https://localhost:8080/contas \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET https://localhost:8080/contas HTTP/1.1
Host: localhost:8080
Accept: application/json

```

```javascript

const headers = {
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://localhost:8080/contas',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'https://localhost:8080/contas',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('https://localhost:8080/contas', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','https://localhost:8080/contas', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/contas");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "https://localhost:8080/contas", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /contas`

*Lista as contas do usuário*

> Example responses

> 200 Response

```json
[
  {
    "id": 0,
    "nomePersonalizado": "string",
    "tipo": "string"
  }
]
```

<h3 id="get__contas-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|Lista de contas retornada com sucesso.|Inline|

<h3 id="get__contas-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|*anonymous*|[[ContaResponse](#schemacontaresponse)]|false|none|none|
|» id|integer(int64)|false|none|none|
|» nomePersonalizado|string|false|none|none|
|» tipo|string|false|none|none|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## post__contas

> Code samples

```shell
# You can also use wget
curl -X POST https://localhost:8080/contas \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
POST https://localhost:8080/contas HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json

```

```javascript
const inputBody = '{
  "nomePersonalizado": "Conta Corrente Principal",
  "tipo": "CONTA_CORRENTE"
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://localhost:8080/contas',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.post 'https://localhost:8080/contas',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.post('https://localhost:8080/contas', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','https://localhost:8080/contas', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/contas");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "https://localhost:8080/contas", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /contas`

*Cria uma nova conta*

> Body parameter

```json
{
  "nomePersonalizado": "Conta Corrente Principal",
  "tipo": "CONTA_CORRENTE"
}
```

<h3 id="post__contas-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[ContaRequest](#schemacontarequest)|true|none|

> Example responses

> 201 Response

```json
{
  "id": 0,
  "nomePersonalizado": "string",
  "tipo": "string"
}
```

<h3 id="post__contas-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Conta criada com sucesso.|[ContaResponse](#schemacontaresponse)|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

<h1 id="zeni-api-de-gest-o-financeira-cart-es-de-cr-dito">Cartões de Crédito</h1>

## get__cartoes

> Code samples

```shell
# You can also use wget
curl -X GET https://localhost:8080/cartoes \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET https://localhost:8080/cartoes HTTP/1.1
Host: localhost:8080
Accept: application/json

```

```javascript

const headers = {
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://localhost:8080/cartoes',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'https://localhost:8080/cartoes',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('https://localhost:8080/cartoes', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','https://localhost:8080/cartoes', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/cartoes");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "https://localhost:8080/cartoes", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /cartoes`

*Lista os cartões de crédito do usuário*

> Example responses

> 200 Response

```json
[
  {
    "id": 0,
    "nomeApelido": "string",
    "bandeira": "string"
  }
]
```

<h3 id="get__cartoes-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|Lista de cartões retornada com sucesso.|Inline|

<h3 id="get__cartoes-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|*anonymous*|[[CartaoCreditoResponse](#schemacartaocreditoresponse)]|false|none|none|
|» id|integer(int64)|false|none|none|
|» nomeApelido|string|false|none|none|
|» bandeira|string|false|none|none|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## post__cartoes

> Code samples

```shell
# You can also use wget
curl -X POST https://localhost:8080/cartoes \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
POST https://localhost:8080/cartoes HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Accept: application/json

```

```javascript
const inputBody = '{
  "nomeApelido": "Cartão Principal",
  "bandeira": "Visa",
  "diaVencimento": 10
}';
const headers = {
  'Content-Type':'application/json',
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://localhost:8080/cartoes',
{
  method: 'POST',
  body: inputBody,
  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Content-Type' => 'application/json',
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.post 'https://localhost:8080/cartoes',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Content-Type': 'application/json',
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.post('https://localhost:8080/cartoes', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Content-Type' => 'application/json',
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','https://localhost:8080/cartoes', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/cartoes");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Content-Type": []string{"application/json"},
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "https://localhost:8080/cartoes", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /cartoes`

*(HU-05) Cadastra um novo cartão de crédito*

> Body parameter

```json
{
  "nomeApelido": "Cartão Principal",
  "bandeira": "Visa",
  "diaVencimento": 10
}
```

<h3 id="post__cartoes-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|body|body|[CartaoCreditoRequest](#schemacartaocreditorequest)|true|none|

> Example responses

> 201 Response

```json
{
  "id": 0,
  "nomeApelido": "string",
  "bandeira": "string"
}
```

<h3 id="post__cartoes-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Cartão criado com sucesso.|[CartaoCreditoResponse](#schemacartaocreditoresponse)|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

<h1 id="zeni-api-de-gest-o-financeira-faturas">Faturas</h1>

## get__cartoes_{cartaoId}_faturas

> Code samples

```shell
# You can also use wget
curl -X GET https://localhost:8080/cartoes/{cartaoId}/faturas \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET https://localhost:8080/cartoes/{cartaoId}/faturas HTTP/1.1
Host: localhost:8080
Accept: application/json

```

```javascript

const headers = {
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://localhost:8080/cartoes/{cartaoId}/faturas',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'https://localhost:8080/cartoes/{cartaoId}/faturas',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('https://localhost:8080/cartoes/{cartaoId}/faturas', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','https://localhost:8080/cartoes/{cartaoId}/faturas', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/cartoes/{cartaoId}/faturas");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "https://localhost:8080/cartoes/{cartaoId}/faturas", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /cartoes/{cartaoId}/faturas`

*(HU-07) Lista as faturas de um cartão*

<h3 id="get__cartoes_{cartaoid}_faturas-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|cartaoId|path|integer|true|none|

> Example responses

> 200 Response

```json
[
  {
    "id": 0,
    "mesReferencia": "2025-10",
    "valorTotal": 0.1,
    "status": "ABERTA"
  }
]
```

<h3 id="get__cartoes_{cartaoid}_faturas-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|Faturas retornadas com sucesso.|Inline|

<h3 id="get__cartoes_{cartaoid}_faturas-responseschema">Response Schema</h3>

Status Code **200**

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|*anonymous*|[[FaturaResponse](#schemafaturaresponse)]|false|none|none|
|» id|integer(int64)|false|none|none|
|» mesReferencia|string|false|none|none|
|» valorTotal|number(double)|false|none|none|
|» status|string|false|none|none|

#### Enumerated Values

|Property|Value|
|---|---|
|status|ABERTA|
|status|FECHADA|
|status|PAGA|
|status|VENCIDA|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## get__faturas_{faturaId}

> Code samples

```shell
# You can also use wget
curl -X GET https://localhost:8080/faturas/{faturaId} \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET https://localhost:8080/faturas/{faturaId} HTTP/1.1
Host: localhost:8080
Accept: application/json

```

```javascript

const headers = {
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://localhost:8080/faturas/{faturaId}',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'https://localhost:8080/faturas/{faturaId}',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('https://localhost:8080/faturas/{faturaId}', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','https://localhost:8080/faturas/{faturaId}', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/faturas/{faturaId}");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "https://localhost:8080/faturas/{faturaId}", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /faturas/{faturaId}`

*Detalha uma fatura específica*

<h3 id="get__faturas_{faturaid}-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|faturaId|path|integer|true|none|

> Example responses

> 200 Response

```json
{
  "id": 0,
  "mesReferencia": "2025-10",
  "valorTotal": 0.1,
  "status": "string",
  "transacoes": [
    {
      "id": 0,
      "descricao": "string",
      "valor": 0.1,
      "data": "2019-08-24",
      "tipo": "string",
      "categoriaNome": "string"
    }
  ]
}
```

<h3 id="get__faturas_{faturaid}-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|Detalhes da fatura retornados com sucesso.|[FaturaDetalhadaResponse](#schemafaturadetalhadaresponse)|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Fatura não encontrada.|None|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## patch__faturas_{faturaId}_pagar

> Code samples

```shell
# You can also use wget
curl -X PATCH https://localhost:8080/faturas/{faturaId}/pagar \
  -H 'Authorization: Bearer {access-token}'

```

```http
PATCH https://localhost:8080/faturas/{faturaId}/pagar HTTP/1.1
Host: localhost:8080

```

```javascript

const headers = {
  'Authorization':'Bearer {access-token}'
};

fetch('https://localhost:8080/faturas/{faturaId}/pagar',
{
  method: 'PATCH',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.patch 'https://localhost:8080/faturas/{faturaId}/pagar',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Authorization': 'Bearer {access-token}'
}

r = requests.patch('https://localhost:8080/faturas/{faturaId}/pagar', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('PATCH','https://localhost:8080/faturas/{faturaId}/pagar', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/faturas/{faturaId}/pagar");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("PATCH");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("PATCH", "https://localhost:8080/faturas/{faturaId}/pagar", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`PATCH /faturas/{faturaId}/pagar`

*Marca uma fatura como paga*

<h3 id="patch__faturas_{faturaid}_pagar-parameters">Parameters</h3>

|Name|In|Type|Required|Description|
|---|---|---|---|---|
|faturaId|path|integer|true|none|

<h3 id="patch__faturas_{faturaid}_pagar-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|204|[No Content](https://tools.ietf.org/html/rfc7231#section-6.3.5)|Fatura marcada como paga com sucesso.|None|
|400|[Bad Request](https://tools.ietf.org/html/rfc7231#section-6.5.1)|Ação não permitida (ex. fatura ainda está aberta).|None|
|404|[Not Found](https://tools.ietf.org/html/rfc7231#section-6.5.4)|Fatura não encontrada.|None|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

<h1 id="zeni-api-de-gest-o-financeira-dashboard-and-relat-rios">Dashboard & Relatórios</h1>

## get__dashboard

> Code samples

```shell
# You can also use wget
curl -X GET https://localhost:8080/dashboard \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET https://localhost:8080/dashboard HTTP/1.1
Host: localhost:8080
Accept: application/json

```

```javascript

const headers = {
  'Accept':'application/json',
  'Authorization':'Bearer {access-token}'
};

fetch('https://localhost:8080/dashboard',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => 'application/json',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'https://localhost:8080/dashboard',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': 'application/json',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('https://localhost:8080/dashboard', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => 'application/json',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','https://localhost:8080/dashboard', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/dashboard");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"application/json"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "https://localhost:8080/dashboard", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /dashboard`

*(HU-09) Retorna dados para o dashboard*

> Example responses

> 200 Response

```json
{
  "saldoGeral": 0.1,
  "totalReceitasMes": 0.1,
  "totalDespesasMes": 0.1,
  "limitesPorCategoria": [
    {
      "categoriaNome": "string",
      "valorGasto": 0.1,
      "valorLimite": 0.1
    }
  ]
}
```

<h3 id="get__dashboard-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|Dados do dashboard retornados com sucesso.|[DashboardResponse](#schemadashboardresponse)|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## get__relatorios_exportar

> Code samples

```shell
# You can also use wget
curl -X GET https://localhost:8080/relatorios/exportar \
  -H 'Accept: application/vnd.ms-excel' \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET https://localhost:8080/relatorios/exportar HTTP/1.1
Host: localhost:8080
Accept: application/vnd.ms-excel

```

```javascript

const headers = {
  'Accept':'application/vnd.ms-excel',
  'Authorization':'Bearer {access-token}'
};

fetch('https://localhost:8080/relatorios/exportar',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Accept' => 'application/vnd.ms-excel',
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'https://localhost:8080/relatorios/exportar',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Accept': 'application/vnd.ms-excel',
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('https://localhost:8080/relatorios/exportar', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Accept' => 'application/vnd.ms-excel',
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','https://localhost:8080/relatorios/exportar', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/relatorios/exportar");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Accept": []string{"application/vnd.ms-excel"},
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "https://localhost:8080/relatorios/exportar", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /relatorios/exportar`

*(HU-10) Exporta relatório de transações*

> Example responses

> 200 Response

<h3 id="get__relatorios_exportar-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|Arquivo Excel gerado com sucesso.|string|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

<h1 id="zeni-api-de-gest-o-financeira-admin">Admin</h1>

## get__admin_parcerias

> Code samples

```shell
# You can also use wget
curl -X GET https://localhost:8080/admin/parcerias \
  -H 'Authorization: Bearer {access-token}'

```

```http
GET https://localhost:8080/admin/parcerias HTTP/1.1
Host: localhost:8080

```

```javascript

const headers = {
  'Authorization':'Bearer {access-token}'
};

fetch('https://localhost:8080/admin/parcerias',
{
  method: 'GET',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.get 'https://localhost:8080/admin/parcerias',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Authorization': 'Bearer {access-token}'
}

r = requests.get('https://localhost:8080/admin/parcerias', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('GET','https://localhost:8080/admin/parcerias', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/admin/parcerias");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("GET");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("GET", "https://localhost:8080/admin/parcerias", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`GET /admin/parcerias`

*(Admin) Lista todas as parcerias*

<h3 id="get__admin_parcerias-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|Lista de parcerias retornada.|None|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

## post__admin_parcerias

> Code samples

```shell
# You can also use wget
curl -X POST https://localhost:8080/admin/parcerias \
  -H 'Authorization: Bearer {access-token}'

```

```http
POST https://localhost:8080/admin/parcerias HTTP/1.1
Host: localhost:8080

```

```javascript

const headers = {
  'Authorization':'Bearer {access-token}'
};

fetch('https://localhost:8080/admin/parcerias',
{
  method: 'POST',

  headers: headers
})
.then(function(res) {
    return res.json();
}).then(function(body) {
    console.log(body);
});

```

```ruby
require 'rest-client'
require 'json'

headers = {
  'Authorization' => 'Bearer {access-token}'
}

result = RestClient.post 'https://localhost:8080/admin/parcerias',
  params: {
  }, headers: headers

p JSON.parse(result)

```

```python
import requests
headers = {
  'Authorization': 'Bearer {access-token}'
}

r = requests.post('https://localhost:8080/admin/parcerias', headers = headers)

print(r.json())

```

```php
<?php

require 'vendor/autoload.php';

$headers = array(
    'Authorization' => 'Bearer {access-token}',
);

$client = new \GuzzleHttp\Client();

// Define array of request body.
$request_body = array();

try {
    $response = $client->request('POST','https://localhost:8080/admin/parcerias', array(
        'headers' => $headers,
        'json' => $request_body,
       )
    );
    print_r($response->getBody()->getContents());
 }
 catch (\GuzzleHttp\Exception\BadResponseException $e) {
    // handle exception or api errors.
    print_r($e->getMessage());
 }

 // ...

```

```java
URL obj = new URL("https://localhost:8080/admin/parcerias");
HttpURLConnection con = (HttpURLConnection) obj.openConnection();
con.setRequestMethod("POST");
int responseCode = con.getResponseCode();
BufferedReader in = new BufferedReader(
    new InputStreamReader(con.getInputStream()));
String inputLine;
StringBuffer response = new StringBuffer();
while ((inputLine = in.readLine()) != null) {
    response.append(inputLine);
}
in.close();
System.out.println(response.toString());

```

```go
package main

import (
       "bytes"
       "net/http"
)

func main() {

    headers := map[string][]string{
        "Authorization": []string{"Bearer {access-token}"},
    }

    data := bytes.NewBuffer([]byte{jsonReq})
    req, err := http.NewRequest("POST", "https://localhost:8080/admin/parcerias", data)
    req.Header = headers

    client := &http.Client{}
    resp, err := client.Do(req)
    // ...
}

```

`POST /admin/parcerias`

*(HU-11, Admin) Cria uma nova parceria*

<h3 id="post__admin_parcerias-responses">Responses</h3>

|Status|Meaning|Description|Schema|
|---|---|---|---|
|201|[Created](https://tools.ietf.org/html/rfc7231#section-6.3.2)|Parceria criada com sucesso.|None|

<aside class="warning">
To perform this operation, you must be authenticated by means of one of the following methods:
bearerAuth
</aside>

# Schemas

<h2 id="tocS_CadastroRequest">CadastroRequest</h2>
<!-- backwards compatibility -->
<a id="schemacadastrorequest"></a>
<a id="schema_CadastroRequest"></a>
<a id="tocScadastrorequest"></a>
<a id="tocscadastrorequest"></a>

```json
{
  "nome": "Usuário Exemplo",
  "email": "usuario@example.com",
  "senha": "senhaForte123"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|nome|string|false|none|none|
|email|string(email)|false|none|none|
|senha|string(password)|false|none|none|

<h2 id="tocS_LoginRequest">LoginRequest</h2>
<!-- backwards compatibility -->
<a id="schemaloginrequest"></a>
<a id="schema_LoginRequest"></a>
<a id="tocSloginrequest"></a>
<a id="tocsloginrequest"></a>

```json
{
  "email": "usuario@example.com",
  "senha": "senhaForte123"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|email|string(email)|false|none|none|
|senha|string(password)|false|none|none|

<h2 id="tocS_LoginResponse">LoginResponse</h2>
<!-- backwards compatibility -->
<a id="schemaloginresponse"></a>
<a id="schema_LoginResponse"></a>
<a id="tocSloginresponse"></a>
<a id="tocsloginresponse"></a>

```json
{
  "token": "string",
  "usuario": {
    "id": 0,
    "nome": "string",
    "email": "user@example.com",
    "tipoUsuario": "PADRAO"
  }
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|token|string|false|none|none|
|usuario|[UsuarioResponse](#schemausuarioresponse)|false|none|none|

<h2 id="tocS_UsuarioResponse">UsuarioResponse</h2>
<!-- backwards compatibility -->
<a id="schemausuarioresponse"></a>
<a id="schema_UsuarioResponse"></a>
<a id="tocSusuarioresponse"></a>
<a id="tocsusuarioresponse"></a>

```json
{
  "id": 0,
  "nome": "string",
  "email": "user@example.com",
  "tipoUsuario": "PADRAO"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|id|integer(int64)|false|none|none|
|nome|string|false|none|none|
|email|string(email)|false|none|none|
|tipoUsuario|string|false|none|none|

#### Enumerated Values

|Property|Value|
|---|---|
|tipoUsuario|PADRAO|
|tipoUsuario|ADMIN|

<h2 id="tocS_TransacaoRequest">TransacaoRequest</h2>
<!-- backwards compatibility -->
<a id="schematransacaorequest"></a>
<a id="schema_TransacaoRequest"></a>
<a id="tocStransacaorequest"></a>
<a id="tocstransacaorequest"></a>

```json
{
  "descricao": "Almoço no restaurante",
  "valor": 45.5,
  "data": "2025-10-13",
  "tipo": "RECEITA",
  "categoriaId": 0,
  "contaId": 0,
  "cartaoId": 0
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|descricao|string|false|none|none|
|valor|number(double)|false|none|none|
|data|string(date)|false|none|none|
|tipo|string|false|none|none|
|categoriaId|integer(int64)|false|none|none|
|contaId|integer(int64)¦null|false|none|none|
|cartaoId|integer(int64)¦null|false|none|none|

#### Enumerated Values

|Property|Value|
|---|---|
|tipo|RECEITA|
|tipo|DESPESA|

<h2 id="tocS_TransacaoResponse">TransacaoResponse</h2>
<!-- backwards compatibility -->
<a id="schematransacaoresponse"></a>
<a id="schema_TransacaoResponse"></a>
<a id="tocStransacaoresponse"></a>
<a id="tocstransacaoresponse"></a>

```json
{
  "id": 0,
  "descricao": "string",
  "valor": 0.1,
  "data": "2019-08-24",
  "tipo": "string",
  "categoriaNome": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|id|integer(int64)|false|none|none|
|descricao|string|false|none|none|
|valor|number(double)|false|none|none|
|data|string(date)|false|none|none|
|tipo|string|false|none|none|
|categoriaNome|string|false|none|none|

<h2 id="tocS_CategoriaRequest">CategoriaRequest</h2>
<!-- backwards compatibility -->
<a id="schemacategoriarequest"></a>
<a id="schema_CategoriaRequest"></a>
<a id="tocScategoriarequest"></a>
<a id="tocscategoriarequest"></a>

```json
{
  "nome": "Lazer",
  "limiteGasto": 500
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|nome|string|false|none|none|
|limiteGasto|number(double)|false|none|none|

<h2 id="tocS_CategoriaResponse">CategoriaResponse</h2>
<!-- backwards compatibility -->
<a id="schemacategoriaresponse"></a>
<a id="schema_CategoriaResponse"></a>
<a id="tocScategoriaresponse"></a>
<a id="tocscategoriaresponse"></a>

```json
{
  "id": 0,
  "nome": "string",
  "limiteGasto": 0.1
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|id|integer(int64)|false|none|none|
|nome|string|false|none|none|
|limiteGasto|number(double)|false|none|none|

<h2 id="tocS_ContaRequest">ContaRequest</h2>
<!-- backwards compatibility -->
<a id="schemacontarequest"></a>
<a id="schema_ContaRequest"></a>
<a id="tocScontarequest"></a>
<a id="tocscontarequest"></a>

```json
{
  "nomePersonalizado": "Conta Corrente Principal",
  "tipo": "CONTA_CORRENTE"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|nomePersonalizado|string|false|none|none|
|tipo|string|false|none|none|

#### Enumerated Values

|Property|Value|
|---|---|
|tipo|CONTA_CORRENTE|
|tipo|POUPANCA|
|tipo|CARTEIRA|

<h2 id="tocS_ContaResponse">ContaResponse</h2>
<!-- backwards compatibility -->
<a id="schemacontaresponse"></a>
<a id="schema_ContaResponse"></a>
<a id="tocScontaresponse"></a>
<a id="tocscontaresponse"></a>

```json
{
  "id": 0,
  "nomePersonalizado": "string",
  "tipo": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|id|integer(int64)|false|none|none|
|nomePersonalizado|string|false|none|none|
|tipo|string|false|none|none|

<h2 id="tocS_CartaoCreditoRequest">CartaoCreditoRequest</h2>
<!-- backwards compatibility -->
<a id="schemacartaocreditorequest"></a>
<a id="schema_CartaoCreditoRequest"></a>
<a id="tocScartaocreditorequest"></a>
<a id="tocscartaocreditorequest"></a>

```json
{
  "nomeApelido": "Cartão Principal",
  "bandeira": "Visa",
  "diaVencimento": 10
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|nomeApelido|string|false|none|none|
|bandeira|string|false|none|none|
|diaVencimento|integer|false|none|none|

<h2 id="tocS_CartaoCreditoResponse">CartaoCreditoResponse</h2>
<!-- backwards compatibility -->
<a id="schemacartaocreditoresponse"></a>
<a id="schema_CartaoCreditoResponse"></a>
<a id="tocScartaocreditoresponse"></a>
<a id="tocscartaocreditoresponse"></a>

```json
{
  "id": 0,
  "nomeApelido": "string",
  "bandeira": "string"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|id|integer(int64)|false|none|none|
|nomeApelido|string|false|none|none|
|bandeira|string|false|none|none|

<h2 id="tocS_FaturaResponse">FaturaResponse</h2>
<!-- backwards compatibility -->
<a id="schemafaturaresponse"></a>
<a id="schema_FaturaResponse"></a>
<a id="tocSfaturaresponse"></a>
<a id="tocsfaturaresponse"></a>

```json
{
  "id": 0,
  "mesReferencia": "2025-10",
  "valorTotal": 0.1,
  "status": "ABERTA"
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|id|integer(int64)|false|none|none|
|mesReferencia|string|false|none|none|
|valorTotal|number(double)|false|none|none|
|status|string|false|none|none|

#### Enumerated Values

|Property|Value|
|---|---|
|status|ABERTA|
|status|FECHADA|
|status|PAGA|
|status|VENCIDA|

<h2 id="tocS_FaturaDetalhadaResponse">FaturaDetalhadaResponse</h2>
<!-- backwards compatibility -->
<a id="schemafaturadetalhadaresponse"></a>
<a id="schema_FaturaDetalhadaResponse"></a>
<a id="tocSfaturadetalhadaresponse"></a>
<a id="tocsfaturadetalhadaresponse"></a>

```json
{
  "id": 0,
  "mesReferencia": "2025-10",
  "valorTotal": 0.1,
  "status": "string",
  "transacoes": [
    {
      "id": 0,
      "descricao": "string",
      "valor": 0.1,
      "data": "2019-08-24",
      "tipo": "string",
      "categoriaNome": "string"
    }
  ]
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|id|integer(int64)|false|none|none|
|mesReferencia|string|false|none|none|
|valorTotal|number(double)|false|none|none|
|status|string|false|none|none|
|transacoes|[[TransacaoResponse](#schematransacaoresponse)]|false|none|none|

<h2 id="tocS_DashboardResponse">DashboardResponse</h2>
<!-- backwards compatibility -->
<a id="schemadashboardresponse"></a>
<a id="schema_DashboardResponse"></a>
<a id="tocSdashboardresponse"></a>
<a id="tocsdashboardresponse"></a>

```json
{
  "saldoGeral": 0.1,
  "totalReceitasMes": 0.1,
  "totalDespesasMes": 0.1,
  "limitesPorCategoria": [
    {
      "categoriaNome": "string",
      "valorGasto": 0.1,
      "valorLimite": 0.1
    }
  ]
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|saldoGeral|number(double)|false|none|none|
|totalReceitasMes|number(double)|false|none|none|
|totalDespesasMes|number(double)|false|none|none|
|limitesPorCategoria|[[DashboardResponse_limitesPorCategoria](#schemadashboardresponse_limitesporcategoria)]|false|none|none|

<h2 id="tocS_DashboardResponse_limitesPorCategoria">DashboardResponse_limitesPorCategoria</h2>
<!-- backwards compatibility -->
<a id="schemadashboardresponse_limitesporcategoria"></a>
<a id="schema_DashboardResponse_limitesPorCategoria"></a>
<a id="tocSdashboardresponse_limitesporcategoria"></a>
<a id="tocsdashboardresponse_limitesporcategoria"></a>

```json
{
  "categoriaNome": "string",
  "valorGasto": 0.1,
  "valorLimite": 0.1
}

```

### Properties

|Name|Type|Required|Restrictions|Description|
|---|---|---|---|---|
|categoriaNome|string|false|none|none|
|valorGasto|number(double)|false|none|none|
|valorLimite|number(double)|false|none|none|

