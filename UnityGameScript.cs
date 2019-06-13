using System.Collections;
using System.Collections.Generic;
using UnityEngine;



public class target : MonoBehaviour
{
	public Transform[] position;
	public int counter = 0;
	public UnityEngine.AI.NavMeshagent agent;
	public Animation anim;
	public bool valid = true;
	public AudioSource shootSound;
	public void MovingAnimal()
	{  

		agent.SetDestination (position [counter].position);
		anim.Play ("galop");
	}
		



    public float health= 10f;
	public int death = 0;

    public void TakeDamage( float amount)
    {
        health -= amount;
        if (health <= 0)
			Die();
    }
    void Die()
	{
		death = 1;
		anim.Play ("getHit");
		StartCoroutine (Halt ());

    }
	IEnumerator Halt()
	{
		agent.Stop ();
		shootSound.Play ();
		yield return new WaitForSeconds(0.5f);
		anim.Play("death");
		yield return new WaitForSeconds(2.0f);
		Destroy (gameObject);
		GameObject.FindObjectOfType<riffel> ().score++;
	}

	void Update ()
	{
		
		if (GameObject.FindObjectOfType<riffel> ().run == 1)
		     {
			if (death == 0) {
				MovingAnimal ();
			}
				if (Vector3.Distance (position [counter].position, gameObject.transform.position) < 5f) 
				{
					counter = Random.Range (0, 3);
					agent.SetDestination (position [counter].position);
				}	
			
		}
	}

	void OnTriggerEnter(Collider hit)
	{
		Debug.Log ("Valid");
		if (hit.CompareTag("animal"))
		{
			Debug.Log ("Un-Valid");
			valid = false;
		}

	}

}