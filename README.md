# Flat File Creator API Documentation
Flat File Creator API Documentation

## Version: 1.0

### Terms of service
urn:tos


**License:** [Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0)

### /api/flatfile

#### POST
##### Summary:

Generate a flat file

##### Description:

Generates a flat file based on the provided request data

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| records | body | List of record DTOs to be included in created flat file | Yes | [ [FlatFileRecordDTO](#FlatFileRecordDTO) ] |

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | string |
| 201 | Created |  |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |
| 404 | Not Found |  |

### /api/reporting

#### POST
##### Summary:

Generate a PDF report from a flat file

##### Description:

Generates a PDF report based on the existing flat file

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | byte |
| 201 | Created |  |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |
| 404 | Not Found |  |

### /error

#### GET
##### Summary:

errorHtml

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | [ModelAndView](#ModelAndView) |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |
| 404 | Not Found |  |

#### POST
##### Summary:

errorHtml

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | [ModelAndView](#ModelAndView) |
| 201 | Created |  |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |
| 404 | Not Found |  |

#### PUT
##### Summary:

errorHtml

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | [ModelAndView](#ModelAndView) |
| 201 | Created |  |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |
| 404 | Not Found |  |

#### DELETE
##### Summary:

errorHtml

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | [ModelAndView](#ModelAndView) |
| 204 | No Content |  |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |

#### OPTIONS
##### Summary:

errorHtml

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | [ModelAndView](#ModelAndView) |
| 204 | No Content |  |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |

#### PATCH
##### Summary:

errorHtml

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | [ModelAndView](#ModelAndView) |
| 204 | No Content |  |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |

### Models


#### FlatFileRecordDTO

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| amount | number |  | No |
| date | string |  | No |
| referenceNo | string |  | No |
| remark | string |  | No |
| status | string |  | No |

#### ModelAndView

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| empty | boolean |  | No |
| model | object |  | No |
| modelMap | object |  | No |
| reference | boolean |  | No |
| status | string |  | No |
| view | [View](#View) |  | No |
| viewName | string |  | No |

#### View

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| contentType | string |  | No |
