AI Assistant chatbot

This is a simple Spring Boot chatbot project (Maven). The repository contains source under `src/main/java`, resources under `src/main/resources`, and a `pom.xml`.

Recommended repository name on GitHub: `AI-Assistant-chatbot` (GitHub repository names should avoid spaces).

How to create a GitHub repo and push this project (quick steps):

1. Initialize a local git repository (if not already):

   git init
   git add .
   git commit -m "Initial commit"

2a. (Recommended) Using GitHub CLI (gh):

   gh repo create "AI-Assistant-chatbot" --public --description "AI Assistant chatbot" --source=. --remote=origin --push

   This requires `gh` to be installed and authenticated (run `gh auth login`).

2b. Or create a repo on github.com manually named `AI-Assistant-chatbot`, then run:

   git remote add origin https://github.com/<USERNAME>/AI-Assistant-chatbot.git
   git branch -M main
   git push -u origin main

2c. Or create the repo via the GitHub API using a Personal Access Token (replace TOKEN):

   curl -H "Authorization: token TOKEN" https://api.github.com/user/repos -d "{\"name\": \"AI-Assistant-chatbot\", \"description\": \"AI Assistant chatbot\"}"
   git remote add origin https://github.com/<USERNAME>/AI-Assistant-chatbot.git
   git branch -M main
   git push -u origin main

Notes:
- If you want a different name or to include spaces, GitHub will usually convert spaces to hyphens in the repo URL; using hyphens is more reliable.
- If you want me to perform the GitHub creation and push for you, I can do so if you provide a GitHub Personal Access Token (with repo scope) or allow me to run `gh` authenticated on your machine. For security, do not paste tokens publicly; if you prefer, authenticate locally with `gh auth login` and I can provide commands to run.
