# Principal git commands

### Git configuration
```shell
git config --global user.name "My name"
git config --global user.email mymail@mail.com
git config --global merge.tool /usr/bin/meld
git config --list
```

### Git help
```shell
git help <command>
git <command> --help
man git-<command>
```

### Create a new repo
```shell
git init
```

### Clone an existing repo
```shell
git clone [url]
```

### Check repo status
```shell
git status
```

### Ignoring files in git
```shell
Modify file ".gitignore"
```

### See "no-added" elements
```shell
git diff
```

### See "added" elements
```shell
git diff --cached
```

### Deleting files
```shell
rm myFile.txt
git rm myFile.txt
```

### Deleting added files
```shell
rm myAddedFile.txt
git rm -f myAddedFile.txt
```

### Revert "add"
```shell
git rm --cached myAddedFile.txt
```

### Rename file
```shell
git mv myOldFile myNewFile
```

### See revisions
```shell
git log
git log -p -2
git log -U1 --word-diff
git log --stat
git log --pretty=online
git log --pretty=format:"%h - %an, %ar : %s"
git log --since=2.weeks
```

### Add forgotten files to last commit
```shell
git commit -m "My commit"
git add myMissedFile
git commit --amend
```

### Revert modifications of a file
```shell
git checkout -- myFile
```

### List remote repos
```shell
git remote -v
```

### Get changes of remote repo
```shell
git fetch [remote-repo-name]
```

### Push to specific remote repo
```shell
git push origin [remote-repo-name]
```

### Pull to specific remote repo (git pull does a git fetch followed by a git merge)
```shell
git pull origin [remote-repo-name]
```

### Inspect remote repo
```shell
git remote show origin
```

### Rename remote repo
```shell
git remote rename myOldName myNewName
```

### Remove remote repo
```shell
git remote rm [remote-repo-name]
```

### List repo tags
```shell
git tag
```

### Create a tag
```shell
git tag -a <tag-name> -m 'My comment'
```

### Create a "fast tag"
```shell
git tag <tag-name>
```

### Tag an old revision
```shell
git tag -a <tag-name> -m 'My comment' <old-revision>
```

### Push a tag into remote server
```shell
git push origin <tag-name>
```

### List branches
```shell
git branch
git branch -v
git branch --merged
git branch --no-merged
```

### Create a branch
```shell
git branch <branch-name>
```

### Move to another branch
```shell
git checkout <branch-name>
```

### Create branch and moving to it
```shell
git checkout -b <branch-name>
```

### Merging branches: myBranch --> master
```shell
git checkout master
git merge myBranch
```

### Delete a branch
```shell
git branch -d <branch-name>
```

### Force to delete an unmerged branch
```shell
git branch -D <branch-name>
```

### Publish a branch
```shell
git push origin <branch-name>
```

### Create a local branch to work based on a remote branch
```shell
git checkout -b <local-branch> <remote-branch>
git checkout -b defect1 origin/defect1
```

### Delete a remote branch after merged it
```shell
git push origin :<branch-name>
```

### Migrate repo from bitbucket to github preserving all logs
```shell
# Change the name of your current bitbucket origin
git remote rename origin <any-name>   =>  git remote rename origin bitbucket

# Now set origin with your new github url repo
git remote add origin <github-url-repo>  => git remote add origin https://github.com/juanitodread/new-repo.git

# Push your changes to your new "master" (github repo)
git push origin master

# Delete previous bitbucket link
git remote rm <any-name>    => git remote rm bitbucket
```
