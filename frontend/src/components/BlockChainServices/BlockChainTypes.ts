// export type CourseType = {id: number; code: string; level: string; title: string};
export type BlockElement =
    {
        hash: string;
        previousHash: string;
        blockName: string;
        data: string;
        timestamp: number;
        nonce: number;
        executionTime : number
    };

export type BlockCreate =
    {
        data: string,
        difficulty: string,
        attempts: number
    };