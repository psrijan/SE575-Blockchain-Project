// export type CourseType = {id: number; code: string; level: string; title: string};
export type BlockElement =
    {
        query: string;
        block_hash: string;
        nonce: number;
        execution_time_ms: number;
        found: boolean;
        parent_hash: string;
        block_id : string
    };

export type BlockCreate =
    {
        data: string
    };