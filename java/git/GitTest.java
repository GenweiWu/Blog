package com.njust.test.git;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.Assert;
import org.junit.Test;

public class GitTest
{
    
    @Test
    public void gitStatus()
        throws IOException, GitAPIException
    {
        File file = getRemoteGitDir();
        
        //git status
        String result = execCommand(file, "git status");
        System.out.println("[git status]\n" + result);
        
        //git status
        Git git = Git.open(file);
        Status status = git.status().call();
        System.out.println(status.getUntracked());
        System.out.println(status.getAdded());
        
    }
    
    @Test
    public void gitRemoteV_Valid()
        throws IOException
    {
        File file = getRemoteGitDir();
        
        //git remote -v
        String result = execCommand(file, "git remote -v");
        Assert.assertNotSame("", result);
        //System.out.println("[git remote -v]\n" + result);
        
        Git git = Git.open(file);
        String remote = git.getRepository().getConfig().getString("remote", "origin", "url");
        Assert.assertNotNull(remote);
        //System.out.println(remote);
    }
    
    @Test
    public void gitRemoteV_Invalid()
        throws IOException
    {
        File file = getLocalGitDir();
        
        //git remote -v
        String result = execCommand(file, "git remote -v");
        Assert.assertEquals("", result);
        
        Git git = Git.open(file);
        String remote = git.getRepository().getConfig().getString("remote", "origin", "url");
        Assert.assertNull(remote);
    }
    
    public static void main(String[] args)
        throws IOException
    {
    
    }
    
    //这个仓库有对应的远程仓库，可以更新和提交
    private static File getRemoteGitDir()
    {
        return new File("D:\\BACloud\\common-lib");
    }
    
    //这是一个本地的git仓库，没有对应的远程仓库的
    private static File getLocalGitDir()
    {
        return new File("D:\\daveCode\\HelloDemo");
    }
    
    private static String execCommand(File dir, String command)
        throws IOException
    {
        Process process = Runtime.getRuntime().exec(command, null, dir);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(),
            StandardCharsets.UTF_8));
        
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null)
        {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
    
}
