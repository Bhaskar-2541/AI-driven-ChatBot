@echo off
REM Helper script to initialize git and push to GitHub.
REM Requires git installed. For automatic creation, the GitHub CLI (gh) is recommended.

setlocal
set REPO_NAME=AI-Assistant-chatbot
set REPO_DESC=AI Assistant chatbot

REM Initialize repo if needed
git rev-parse --is-inside-work-tree >nul 2>&1
if errorlevel 1 (
  echo Initializing git repository...
  git init
  git add .
  git commit -m "Initial commit"
)

REM Try to use gh if available
where gh >nul 2>&1
if %ERRORLEVEL%==0 (
  echo Creating repository on GitHub using gh...
  gh repo create "%REPO_NAME%" --public --description "%REPO_DESC%" --source=. --remote=origin --push
  goto end
)

echo gh CLI not found. To continue, create a repository on GitHub named %REPO_NAME% and then run the following commands:
echo.
echo git remote add origin https://github.com/USERNAME/%REPO_NAME%.git
echo git branch -M main
echo git push -u origin main
echo.
echo Or create the repo via the API (requires a Personal Access Token):
echo curl -H "Authorization: token TOKEN" https://api.github.com/user/repos -d "{\"name\": \"%REPO_NAME%\", \"description\": \"%REPO_DESC%\"}"

:end
pause
