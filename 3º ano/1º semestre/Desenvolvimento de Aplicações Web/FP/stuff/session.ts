export class Session
{
    private sessionIDs: Array<number>;

    constructor()
    {
        this.sessionIDs = [0];
    }

    handleSessionId(clientSessionID: number): boolean
    {
        if(clientSessionID === -1){
            this.sessionIDs.push(this.sessionIDs[this.sessionIDs.length - 1] + 1);
            return true;
        }

        for(let i = 0; i < this.sessionIDs.length; i++)
            if(clientSessionID === this.sessionIDs[i])
                return true;

        return false;
    }

    deleteSession(clientSessionID: number): number
    {
        if(clientSessionID === -1)
            throw new Error('Something bad happened');

        for(let i = 0; i < this.sessionIDs.length; i++)
            if(clientSessionID === this.sessionIDs[i])
                this.sessionIDs.splice(i, 1);

        return clientSessionID;
    }
    
}