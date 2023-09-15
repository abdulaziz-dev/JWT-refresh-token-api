# JWT-refresh-token-api
Using /refresh-token to keep logged-in in JWT based apps

First, one should regiser via _/api/v1/auth/register_, providing user details (name, phone number) in request body.
Next, user signs in by phone number at _/api/v1/auth/login_. At this time, he will be given **accessToken** and **refreshToken**. Access token is actually JWT token, refresh token is not.
On next requests, user should put the **accessToken** in Authorization header with "Bearer" type. Logged in users can access resouces at _/api/v1/resources_.
Once accessToken expires, user can send request to _/api/v1/auth/refresh-token_ providing his **refresh token** in request body to get a new access token.
Lastly, when user signs out (/api/v1/auth/logout), user's refresh token  will be removed, and he must sign in again to get a new one.
