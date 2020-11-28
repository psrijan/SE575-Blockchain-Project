export type CourseType = {id: number; code: string; level: string; title: string};
export type BlockChain =
    {
        query: string;
        block_hash: string;
        nonce: number;
        execution_time_ms: number;
        found: boolean;
        parent_hash: string;
        "block_id" : string
    };