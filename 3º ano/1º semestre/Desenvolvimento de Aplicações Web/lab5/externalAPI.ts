declare const fetch : any;
const MAIN_API_URL = "https://jsonplaceholder.typicode.com/users"

export async function getAllUsers() 
{
    try 
    {
        const response = await fetch(MAIN_API_URL);
        const data = await response.json();
        return data;
    }
    catch (error) 
    {
        return "Error inside: getAllUsers()";
    }
}

export async function getSomeUsers(city: string) 
{
    try 
    {
        const response = await fetch(MAIN_API_URL + "/?address.city=" + city);
        const data = await response.json();
        return data;
    }
    catch (error) 
    {
        return "Error inside: getSomeUsers()";
    }
}



export interface MyType 
{
    [key: string]: string;
}

function changeEmailDomain(email: string): string
{
    const splitEmail: string[] = email.split("@");
    return splitEmail[0] + "@ualg.pt";
}

export function transformData(element: [string, unknown]) 
{
    const myElement: MyType = element[1] as MyType;
    return {
        id: myElement.id,
        name: myElement.name,
        login: myElement.username,  // this key was changed
        email: changeEmailDomain(myElement.email),
        address: myElement.address,
        phone: myElement.phone,
        website: myElement.website,
        //company: myElement.company -> This one was removed
    };
}