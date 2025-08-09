
# Test API Rest para gestián de envíos

_API sencilla para la gestión de envios de una empresa de logística_

## Endpoints 

_lista de endpoints implementados_

## POST api/shipments/create 

_crear una orden de envío_

Ejemplo request:
```
{
    "source": "UBX1",
    "destination": "UBX2",
    "weight": 15.00,
    "description": "package 1"
}
```

Ejemplo response:
```
{
    "id": 10,
    "code": "eDggsKHy1O",
    "description": "package 1",
    "weight": 15.00,
    "createDate": "2025-08-09T14:10:00.5741376",
    "currentStateId": 1,
    "sourceId": 1,
    "destinationId": 2
}
```

## GET api/shipments/check/{code} 
_consultar estado de un envío_

Ejemplo request: api/shipments/check/eDggsKHy1O

Ejemplo response:
```
{
"packageCode": "eDggsKHy1O",
"state": "Recibido"
}
```


## PUT api/shipments/update/{code}
_actualizar estado de un envío_

Ejemplo request: api/shipments/update/eDggsKHy1O

Ejemplo response:
```
{
"packageCode": "eDggsKHy1O",
"state": "En preparación"
}
```