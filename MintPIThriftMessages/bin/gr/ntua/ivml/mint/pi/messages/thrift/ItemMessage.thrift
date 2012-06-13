namespace java gr.ntua.ivml.mint.pi.messages

struct Namespace{
	1: string prefix,
	2: string uri
}

struct ExtendedParameter{
	1: string parameterName;
	2: string parameterValue
}

struct SchemaValidation{
	1: i32 schema_id,
	2: bool valid
}

struct ItemMessage{
	1: i64 item_id,
	2: string xml,
	3: i32 dataset_id,
	4: i32 org_id,
	5: Namespace prefix,
	6: i64 datestamp,
	7: i32 user_id,
	8: string project,
	9: i64 sourceItem_id,
	10: i32 sourceDataset_id,
	11: i32 schema_id,
	12: optional SchemaValidation valid,
	13: optional list<ExtendedParameter> params
}
