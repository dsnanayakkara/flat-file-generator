# Flat file generator and Jasper Reporting API
Flat file generator and Jasper Reporting API

## Version: 1.0

### Terms of service
urn:tos


**License:** [Apache 2.0](http://www.apache.org/licenses/LICENSE-2.0)

### /api/flatfile/generate

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

### /api/flatfile/report

#### POST
##### Summary:

Generate a PDF report

##### Description:

Generates a PDF report based on the provided request data

##### Parameters

| Name | Located in | Description | Required | Schema |
| ---- | ---------- | ----------- | -------- | ---- |
| records | body | List of flat file record DTOs to be included in the report | Yes | [ [FlatFileRecordDTO](#FlatFileRecordDTO) ] |

##### Responses

| Code | Description | Schema |
| ---- | ----------- | ------ |
| 200 | OK | byte |
| 201 | Created |  |
| 401 | Unauthorized |  |
| 403 | Forbidden |  |
| 404 | Not Found |  |

### Models


#### FlatFileRecordDTO

| Name | Type | Description | Required |
| ---- | ---- | ----------- | -------- |
| amount | number |  | No |
| date | string |  | No |
| referenceNo | string |  | No |
| remark | string |  | No |
| status | string |  | No |
