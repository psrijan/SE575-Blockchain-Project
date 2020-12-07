export class BaseMessage  {
    success: boolean;
    message: string;
    desc: any;
    dto: any;

    constructor (success: boolean, message: string, desc?: string, dto?: any ) {
        this.success = success;
        this.message = message;
        this.desc = desc;
        this.dto = dto;
    }
}