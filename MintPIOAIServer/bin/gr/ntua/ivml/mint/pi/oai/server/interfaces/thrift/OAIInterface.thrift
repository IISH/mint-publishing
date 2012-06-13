namespace java gr.ntua.ivml.mint.pi.oai.server.interfaces

struct ProgressResponse{
	1:i32 insertedRecords,
	2:i32 conflictedRecords,
	3:i32 totalRecords
}

exception RecordNotFound{
	1:string message
}

service OAIServer{
	string createReport(1:string projectName, 2:i32 userId, 3:i32 orgId, 4:list<i32> datasets),
	string fetchReport(1:string reportId),
	void closeReport(1:string reportId),
	void initIndex(1:string projectName),
	void publishRecordByRecordId(1:i64 recordId, 2:string projectName, 3:i32 userId, 4:i32 orgId) throws (1: RecordNotFound cnf),
	void unpublishRecordByRecordId(1:i64 recordId, 2:string projectName, 3:i32 userId, 4:i32 orgId) throws (1: RecordNotFound cnf),
	void unpublishRecordsByOrgId(1:i32 orgId, 2:i32 userId, 3:string projectName),
	void unpublishRecordsByDatasetId(1:i32 orgId, 2:i32 userId, 3:string projectName, 4:i32 datasetId),
	list<string> getReportsByOrgId(1:i32 orgId),
	list<string> getReportsByUserId(1:i32 userId),
	list<string> getReportsByDatasetId(1:i32 datasetId),
	bool isRecordPublished(1:i64 recordId, 2:string projectName),
	bool isDatasetPublished(1:i32 datasetId, 2:string projectName),
	ProgressResponse getProgress(1:string reportId)
}