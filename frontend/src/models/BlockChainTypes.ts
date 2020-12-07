export type BlockElement =
    {
        hash: string;
        previousHash: string;
        blockName: string;
        data: string;
        timestamp: number;
        nonce: number;
        attempts: number;
        executionTime: number;
        valid : boolean
    };

export type BlockCreate =
    {
        data: string,
        difficulty: string,
        attempts: number
    };

